package lk.ijse.carservicesystem.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
public class MainFormController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml")));
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/supplier_form.fxml")));

    }
    public void PaymentOnAction(ActionEvent event) throws IOException {
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/payment_form.fxml")));
    }

    public void ServiceOnAction(ActionEvent event) throws IOException {
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/service_form.fxml")));
    }

    public void VehicleOnAction(ActionEvent event) throws IOException {
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/vehicle_form.fxml")));
    }

    public void EmployeeOnAction(ActionEvent event) throws IOException {
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/employee_form.fxml")));
    }

    public void btnDashboardOnAction(ActionEvent event) throws IOException {
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml")));
    }
}


