package Model;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;
    private String categoryName;

    @ManyToMany(mappedBy = "categoriesResponsible", cascade = { CascadeType.MERGE })
    @OrderBy
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> responsibleUsers;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Category> subCategories;
    @ManyToOne
    private Category parentCategory;
    private float overallFinances;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Receivable> income;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Payment> expense;

    public Category(int categoryID, String categoryName, List<User> responsibleUsers, List<Category> subCategories, Category parentCategory, float overallFinances, List<Receivable> income, List<Payment> expense) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.responsibleUsers = responsibleUsers;
        this.subCategories = subCategories;
        this.parentCategory = parentCategory;
        this.overallFinances = overallFinances;
        this.income = income;
        this.expense = expense;
    }

    public Category(String categoryName, List<User> responsibleUsers, List<Category> subCategories, Category parentCategory, float overallFinances, List<Receivable> income, List<Payment> expense) {
        this.categoryName = categoryName;
        this.responsibleUsers = responsibleUsers;
        this.subCategories = subCategories;
        this.parentCategory = parentCategory;
        this.overallFinances = overallFinances;
        this.income = income;
        this.expense = expense;
    }

    public Category() {
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<User> getResponsibleUsers() {
        return responsibleUsers;
    }

    public void setResponsibleUsers(List<User> responsibleUsers) {
        this.responsibleUsers = responsibleUsers;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public float getOverallFinances() {
        return overallFinances;
    }

    public void setOverallFinances(float overallFinances) {
        this.overallFinances = overallFinances;
    }

    public List<Receivable> getIncome() {
        return income;
    }

    public void setIncome(List<Receivable> income) {
        this.income = income;
    }

    public List<Payment> getExpense() {
        return expense;
    }

    public void setExpense(List<Payment> expense) {
        this.expense = expense;
    }
}
