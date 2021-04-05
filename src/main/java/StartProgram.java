import Controller.LoginPageController;
import Model.Category;
import Model.SystemRoot;
import Model.User;
import Utils.DataRW;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartProgram extends Application {

    SystemRoot systemRoot = new SystemRoot("System", new ArrayList<Category>(),new ArrayList<User>());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Resources/View/LoginPage.fxml"));

        Parent root = loader.load();

        LoginPageController loginPageController = loader.getController();
        loginPageController.setSystemRoot(systemRoot);

        primaryStage.setTitle("Accounting system");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
