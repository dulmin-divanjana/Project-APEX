package lk.ijse.carservicesystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
     stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"))));
     stage.setTitle("login");
     stage.show();
    }
}