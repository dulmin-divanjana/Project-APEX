package lk.ijse.carservicesystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carservicesystem.dto.CustomerDto;
import lk.ijse.carservicesystem.dto.tm.CustomerTm;
import lk.ijse.carservicesystem.model.CustomerModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerFormController {

    @FXML
    private TextField txtEmail;

    @FXML
    private TableColumn<?,?> colEmail;

    @FXML
    private TableColumn<?,?> colContact;

    @FXML
    private TextField txtContact;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;


    private CustomerModel customer = new CustomerModel();

    public void initialize(){
        setCellValueFactory();
        loadAllCustomer();
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    @FXML
    void btnPrintOnAction(ActionEvent event) throws JRException {
        String id = txtId.getText();

        try {
            CustomerDto dto = CustomerModel.searchCustomer(id);
            if(dto!=null){
                viewCustomerReport(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void viewCustomerReport(CustomerDto dto) throws JRException {
        HashMap hashMap = new HashMap();
        hashMap.put("id",dto.getId());
        hashMap.put("name",dto.getName());
        hashMap.put("email",dto.getEmail());
        hashMap.put("contact",dto.getContact());
        hashMap.put("address",dto.getAddress());


        InputStream resourceAsStream =  getClass().getResourceAsStream("/report/Customer_report.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport= JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                hashMap,
                new JREmptyDataSource()
        );

        JasperViewer.viewReport(jasperPrint,false);
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFileds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try{
            boolean isDelete = CustomerModel.deleteCustomer(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer deleted").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private boolean validateId() {
        String customerId = txtId.getText();
        boolean matches = Pattern.matches("[C][0-9]{3,}", customerId);

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer ID. It should start with 'C' followed by at least three digits.").show();
            return false;
        }
        return true;
    }

    private boolean validateContact() {
        String customerContact = txtContact.getText();
        boolean matches = Pattern.matches("[0-9]{10}", customerContact);

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact number. It should be a 10-digit number.").show();
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        String customerEmail = txtEmail.getText();
        boolean matches = Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", customerEmail);

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid email address.").show();
            return false;
        }
        return true;
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (validateFields()) {
            String id = txtId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String contact = txtContact.getText();
            String address = txtAddress.getText();

            var dto = new CustomerDto(id, name, email, contact, address);
            try {
                boolean isSaved = customer.saveCustomer(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved").show();
                    clearFileds();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    private boolean validateFields() {
        return validateId() && validateContact() && validateEmail();
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();

            var dto = new CustomerDto(id,name,email,contact,address);

            try{

                boolean isUpdate = customer.updateCustomer(dto);

                if (isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer is updated").show();
                    clearFileds();
                    initialize();
                }
            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event)  {

        CustomerDto dto = null;
        try {
            dto = customer.searchCustomer(txtId.getText());
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

    private void loadAllCustomer() {
        var model = new CustomerModel();

        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> dtoList = customer.getAllCustomer();

            for (CustomerDto dto : dtoList) {
                obList.add(
                        (new CustomerTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getEmail(),
                                dto.getContact(),
                                dto.getAddress()

                        ))
                );
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}