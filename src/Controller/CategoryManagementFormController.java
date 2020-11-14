package Controller;

import Hibernate.CategoryHibernate;
import Hibernate.PaymentHibernate;
import Hibernate.UserHibernate;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static Utils.CategoryUtils.accessLastCategory;
import static Utils.CategoryUtils.getSelectedCategory;

public class CategoryManagementFormController implements Initializable {

    @FXML public Button accessSelectedCategoryBtn;
    @FXML public ListView categoryList;
    @FXML public Label userLabel;
    @FXML public Label currentUserLabel;
    @FXML public ListView responsibleUsersList;
    @FXML public Label auditDetailsLabel;
    @FXML public ListView incomeList;
    @FXML public ListView expenseList;
    @FXML public Label incomeHistoryLabel;
    @FXML public Label expensesHistoryLabel;
    @FXML public Button sellBtn;
    @FXML public Button buyBtn;
    @FXML public MenuItem addCategoryBtn;
    @FXML public MenuItem deleteCategoryBtn;
    @FXML public Button backToParentBtn;
    @FXML public Label categoryNameLabel;

    private ArrayList<Category> subCategories;
    private User user;
    private Category currentCategory;
    private SystemRoot systemRoot;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("AccountingSystem_GUI_DB");
    private CategoryHibernate categoryHibernate = new CategoryHibernate(factory);
    private PaymentHibernate paymentHibernate = new PaymentHibernate(factory);

    public SystemRoot getSystemRoot() {
        return systemRoot;
    }

    public void setSystemRoot(SystemRoot systemRoot) {
        this.systemRoot = systemRoot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        userLabel.setText("User: " + user.getName());
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory, User user) {
        this.currentCategory = currentCategory;
        currentUserLabel.setText(user.getName());
        populateCategoriesList();
        populateIncomeHistoryList();
        populateExpenseHistoryList();
        populateResponsibleUsersList();
        categoryNameLabel.setText(currentCategory.getCategoryName());
    }



    private void populateResponsibleUsersList() {
        currentCategory.
                getResponsibleUsers().
                forEach
                        (user ->
                                responsibleUsersList.getItems().
                                        add
                                                (user.getName()));
    }

    private void populateExpenseHistoryList() {
        currentCategory.getExpense().
                forEach
                        (payment ->
                                categoryList.getItems().
                                        add
                                                (payment.getPaymentDate().toString() + "  " + payment.getPaymentSum()));
    }

    private void populateIncomeHistoryList() {
        currentCategory.getIncome().
                forEach
                        (receivable ->
                                categoryList.getItems().
                                        add
                                                (receivable.getReceivableDate() + "  " + receivable.getReceivableSum()));
    }

    private void populateCategoriesList() {
        currentCategory.
                getSubCategories().
                forEach
                        (category ->
                                categoryList.getItems().
                                        add
                                                (category.getCategoryName()));
    }




    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<Category> subCategories) { this.subCategories = subCategories; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void accessSelectedCategory(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CategoryManagementForm.fxml"));

        Parent root = loader.load();
        CategoryManagementFormController categoryManagementFormController = loader.getController();


        categoryManagementFormController.setCurrentCategory(getSelectedCategory(categoryList.getSelectionModel().getSelectedItem(),currentCategory.getSubCategories()), user);

        categoryManagementFormController.setUser(user);

        categoryManagementFormController.setSystemRoot(systemRoot);


        Stage stage = (Stage) accessSelectedCategoryBtn.getScene().getWindow();

        stage.setTitle("Accounting system");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void SellSomething(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Income");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter price: ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && result.get() != "")
        {
            currentCategory.getIncome().add( new Receivable(Float.parseFloat(result.get()),LocalDate.now()));//TODO make proper validation in case for not numbers

            System.out.println(currentCategory.getIncome().size());
            Receivable lastReceivable = currentCategory.getIncome().get(currentCategory.getIncome().size()-1);

            incomeList.getItems().add(lastReceivable.getReceivableDate() + "   " + lastReceivable.getReceivableSum());
        }
    }

    public void buySomething(ActionEvent actionEvent) throws Exception {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Payment");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter price: ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && result.get() != "")
        {
            Payment paymentBeingMade = new Payment(Float.parseFloat(result.get()),LocalDate.now());

            currentCategory.getExpense().add(paymentBeingMade);//TODO make proper validation in case for not numbers

            categoryHibernate.AddExpense(currentCategory, paymentBeingMade);

            Payment lastPayment = currentCategory.getExpense().get(currentCategory.getExpense().size()-1);

            expenseList.getItems().add(lastPayment.getPaymentDate() + " " + lastPayment.getPaymentSum());
        }
    }

    public void loadCategoryAddDialog(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Add  category");
        dialog.setHeaderText(null);
        dialog.setContentText("Category name: ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && result.get() != "")
        {
            currentCategory.getSubCategories().add( new Category(result.get(), new ArrayList<User>(), new ArrayList<Category>(), null, 0, new ArrayList<Receivable>(), new ArrayList<Payment>()));
            categoryList.getItems().
                    add(accessLastCategory(currentCategory.getSubCategories()).getCategoryID() + ": "
                            + accessLastCategory(currentCategory.getSubCategories()).getCategoryName());
        }
    }

    public void deleteCategory(ActionEvent actionEvent) {
    }

    public void goToParentCategory(ActionEvent actionEvent) throws IOException {


        if (currentCategory.getParentCategory() != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CategoryManagementForm.fxml"));

            Parent root = loader.load();

            CategoryManagementFormController categoryManagementFormController = loader.getController();

            categoryManagementFormController.setCurrentCategory(currentCategory.getParentCategory(), user);

            categoryManagementFormController.setUser(user);

            categoryManagementFormController.setSystemRoot(systemRoot);



            Stage stage = (Stage) accessSelectedCategoryBtn.getScene().getWindow();

            stage.setTitle("Accounting system");
            stage.setScene(new Scene(root));
            stage.show();

        }
        else
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SystemRootPage.fxml"));

            Parent root = loader.load();

            SystemRootPageController systemRootPageController = loader.getController();
            systemRootPageController.setSystemRoot(systemRoot,user);

            Stage stage = (Stage) backToParentBtn.getScene().getWindow();

            stage.setTitle("Accounting system");
            stage.setScene(new Scene(root));
            stage.show();
        }




    }
}
