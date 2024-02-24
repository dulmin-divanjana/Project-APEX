package lk.ijse.carservicesystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginFormController {

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String username = txtUserName.getText();
        String password = enterPasswordField.getText();

        if ("1".equals(username)) {
            String validUserPassword = "1";

            if (password.equals(validUserPassword)) {
                navigateToMainWindow("/view/main_form.fxml");
                return;
            }

        }

        showErrorDialog("Login Failed", "Invalid username or password. Please try again.");
    }
    /*private void loadDashboard(String dashboardFXMLPath) throws IOException {
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource(dashboardFXMLPath));
            root.getChildren().clear();
            root.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
   /* @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String userName = txtUserName.getText();
        String pw = enterPasswordField.getText();

        navigateToMainWindow();
    }*/

    private void navigateToMainWindow(String s) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));
        Scene scene = new Scene(rootNode);

        root.getChildren().clear();
        Stage primaryStage = (Stage) root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Menu");
    }

    @FXML
    void btnCancleOnAction(ActionEvent event) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText("Cancel Action");
        confirmationAlert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            closeForm();        }

    }

    private void closeForm() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}



