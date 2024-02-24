package lk.ijse.carservicesystem.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carservicesystem.dto.EmployeeDto;
import lk.ijse.carservicesystem.dto.tm.EmployeeTm;
import lk.ijse.carservicesystem.model.EmployeeModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane root;


    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    private EmployeeModel employeeModel = new EmployeeModel();

    public void initialize(){
        setCellValueFactory();
        loadAllEmployee();
    }
    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }



    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFileds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = employeeModel.deleteEmployee(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "employee not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean validateEmployeeId() {
        String employeeId = txtId.getText();
        boolean matches = Pattern.matches("[E][0-9]{3,}", employeeId);

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee ID. It should start with 'E' followed by at least three digits.").show();
            return false;
        }
        return true;
    }
    private boolean validateContact() {
        String contact = txtContact.getText();
        boolean matches = Pattern.matches("\\d{10}", contact);

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact number. It should be a 10-digit number.").show();
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        String email = txtEmail.getText();
        boolean isEmailValid = Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email);

        if (!isEmailValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid email address.").show();
            return false;
        }
        return true;
    }

    private boolean validateEmployee() {
        return validateEmployeeId() && validateContact() && validateEmail();
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (validateEmployee()) {
            String id = txtId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String contact = txtContact.getText();
            String address = txtAddress.getText();

            var dto = new EmployeeDto(id, name, email, contact, address);
            try {
                boolean isSaved = employeeModel.saveEmployee(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "employee saved").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }
    @FXML
    void btnSearchOnAction(ActionEvent event) {

        EmployeeDto dto = null;
        try {
            dto = employeeModel.searchEmployee(txtId.getText());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        txtName.setText(dto.getName());
        txtEmail.setText(dto.getEmail());
        txtContact.setText(dto.getContact());
        txtAddress.setText(dto.getAddress());
    }
    private void clearFileds() {
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtContact.setText("");
        txtAddress.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();

        var dto = new EmployeeDto(id,name,email,contact,address);

        try{

            boolean isUpdate = employeeModel.updateEmployee(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee is updated").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void loadAllEmployee() {
        var model = new EmployeeModel();

        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> dtoList = employeeModel.getAllEmployee();

            for (EmployeeDto dto : dtoList) {
                obList.add(
                        (new EmployeeTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getEmail(),
                                dto.getContact(),
                                dto.getAddress()

                        ))
                );
            }
            tblEmployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


