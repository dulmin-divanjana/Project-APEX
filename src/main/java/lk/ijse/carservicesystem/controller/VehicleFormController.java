package lk.ijse.carservicesystem.controller;

import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.carservicesystem.dto.CustomerDto;
import lk.ijse.carservicesystem.dto.VehicleDto;
import lk.ijse.carservicesystem.dto.tm.VehicleTm;
import lk.ijse.carservicesystem.model.CustomerModel;
import lk.ijse.carservicesystem.model.VehicleModel;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class VehicleFormController {

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private TableColumn<?, ?> colColor;

    @FXML
    private TableView<VehicleTm> tblVehicle;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colMileage;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<?, ?> colCustomer;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtColour;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMileage;

    @FXML
    private TextField txtModel;

    private VehicleModel vehicle = new VehicleModel();

    public void initialize(){
        setCellValueFactory();
        loadAllVehicle();
        loadCustomerId();
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colMileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }
    @FXML
    void cmbCustomerId(ActionEvent event) {
        String customerId = (String) cmbCustomerId.getValue();

        try {
            CustomerDto customerDto = CustomerModel.searchCustomer(customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDto> customerTMS = CustomerModel.getAllCustomer();

            for (CustomerDto dto : customerTMS) {
                obList.add(dto.getId());
            }
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFileds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String id = txtId.getText();

        try{
            boolean isDelete = VehicleModel.deleteModel(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle deleted").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private boolean validateVehicle() {
        String vehicleId = txtId.getText();
        boolean matches = Pattern.matches("[V][0-9]{3,}", vehicleId);

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid vehicle ID. It should start with 'V' followed by at least three digits.").show();
            return false;
        }

        return true;
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (validateVehicle()) {
            String id = txtId.getText();
            String model = txtModel.getText();
            String colour = txtColour.getText();
            String mileage = txtMileage.getText();

            var dto = new VehicleDto(id, model, colour, mileage, cmbCustomerId.getValue());
            try {
                boolean isSaved = vehicle.saveVehicle(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "vehicle saved").show();
                    clearFileds();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {


        VehicleDto dto = null;
        try {
            dto = vehicle.searchVehicle(txtId.getText());
            System.out.println(txtId.getText());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        txtModel.setText(dto.getModel());
        txtColour.setText(dto.getColor());
        txtMileage.setText(dto.getMileage());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String model = txtModel.getText();
        String colour = txtColour.getText();
        String mileage = txtMileage.getText();


        var dto = new VehicleDto(id,model,colour,mileage);

        try{

            boolean isUpdate = vehicle.updateVehicle(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle is updated").show();
                clearFileds();
                initialize();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    private void clearFileds() {
        txtId.setText("");
        txtColour.setText("");
        txtModel.setText("");
        txtMileage.setText("");
    }
    private void loadAllVehicle() {
        var model = new VehicleModel();

        ObservableList<VehicleTm> obList = FXCollections.observableArrayList();

        try {
            List<VehicleDto> dtoList = vehicle.getAllVehicle();

            for (VehicleDto dto : dtoList) {
                obList.add(
                        (new VehicleTm(
                                dto.getId(),
                                dto.getModel(),
                                dto.getColor(),
                                dto.getMileage(),
                                dto.getCustomerId()
                        ))
                );
            }
            tblVehicle.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

