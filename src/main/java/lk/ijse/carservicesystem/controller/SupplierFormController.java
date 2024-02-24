package lk.ijse.carservicesystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carservicesystem.dto.SupplierDto;
import lk.ijse.carservicesystem.dto.tm.SupplierTm;
import lk.ijse.carservicesystem.model.SupplierModel;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class SupplierFormController {

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtName;
    private SupplierModel supplierModel = new SupplierModel();

    private SupplierModel supplier = new SupplierModel();

    public void initialize(){
        setCellValueFactory();
        loadAllSupplier();
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFileds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try{
            boolean isDelete = SupplierModel.deleteSupplier(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier deleted").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private boolean validateSupplier() {
        String supplierId = txtId.getText();
        boolean matches = Pattern.matches("[S][0-9]{3,}", supplierId);

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier ID. It should start with 'S' followed by at least three digits.").show();
            return false;
        }

        String contact = txtContact.getText();
        boolean matches1 = Pattern.matches("\\d{10}", contact);

        if (!matches1) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier contact number.").show();
            return false;
        }

        String email = txtEmail.getText();
        boolean isEmailValid = Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email);

        if (!isEmailValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid email address.").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (validateSupplier()) {
            String id = txtId.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String contact = txtContact.getText();
            String location = txtLocation.getText();

            var dto = new SupplierDto(id, name, email, contact, location);
            try {
                boolean isSaved = supplierModel.saveSupplier(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "supplier saved").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        SupplierDto dto = null;
        try {
            dto = supplier.searchSupplier(txtId.getText());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        txtName.setText(dto.getName());
        txtEmail.setText(dto.getEmail());
        txtContact.setText(dto.getContact());
        txtLocation.setText(dto.getLocation());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String location = txtLocation.getText();

        var dto = new SupplierDto(id,name,email,contact,location);

        try{

            boolean isUpdate = supplier.updateSupplier(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier is updated").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void clearFileds() {
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtContact.setText("");
        txtLocation.setText("");
    }
    private void loadAllSupplier() {
        var model = new SupplierModel();

        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDto> dtoList = supplier.getAllSupplier();

            for (SupplierDto dto : dtoList) {
                obList.add(
                        (new SupplierTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getEmail(),
                                dto.getContact(),
                                dto.getLocation()

                        ))
                );
            }
            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
