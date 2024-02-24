package lk.ijse.carservicesystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carservicesystem.dto.ServiceDto;
import lk.ijse.carservicesystem.dto.VehicleDto;
import lk.ijse.carservicesystem.dto.tm.OrderTm;
import lk.ijse.carservicesystem.model.DeleteOrderModel;
import lk.ijse.carservicesystem.model.ServiceModel;
import lk.ijse.carservicesystem.model.VehicleModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PaymentFormController {
    @FXML
    public Label txtCustomerId;

    @FXML
    private JFXComboBox cmbService;

    @FXML
    private JFXComboBox cmbVehicaleId;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colSerViceId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colVehicaleId;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private Label txtColor;

    @FXML
    private Label txtDate;

    @FXML
    private Label txtModel;

    @FXML
    private Label txtNetTotal;

    @FXML
    private TableView<OrderTm> txtPayment;

    @FXML
    private Label txtServiceTotal;

    @FXML
    private Label txtType;

    ServiceModel serviceModel = new ServiceModel();

    VehicleModel vehicleModel = new VehicleModel();

    DeleteOrderModel deleteModel = new DeleteOrderModel();

    ObservableList<OrderTm> obList = FXCollections.observableArrayList();

    public void initialize(){
        txtDate.setText(String.valueOf(LocalDate.now()));
        setServiceId();
        setVehicaleId();
        txtPayment.setItems(obList);
        setCellValues();
    }

    private void setCellValues() {
        colVehicaleId.setCellValueFactory(new PropertyValueFactory<>("vehicaleID"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
        colSerViceId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totle"));
    }

    private void setVehicaleId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<VehicleDto> dtoList = VehicleModel.getAllVehicle();
            for (VehicleDto dto: dtoList) {
                obList.add(dto.getId());
            }
            cmbVehicaleId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setServiceId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<ServiceDto> dtoList = serviceModel.getAllService();

            for (ServiceDto dto: dtoList) {
                obList.add(dto.getId());
            }
            cmbService.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ServiceOnAction(ActionEvent event) {
        String id = (String) cmbService.getValue();

        try {
            ServiceDto dto = serviceModel.searchService(id);
            txtType.setText(dto.getStatus());
            txtServiceTotal.setText(dto.getPrice());
        } catch (Exception e) {

        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String serID = (String) cmbService.getValue();
        String type = txtType.getText();
        double totle = Double.parseDouble(txtServiceTotal.getText());
        String vehicaleId = (String) cmbVehicaleId.getValue();
        String model = txtModel.getText();

        txtNetTotal.setText(txtServiceTotal.getText());

        obList.add(new OrderTm(vehicaleId,model,serID,type,totle));
        txtPayment.setItems(obList);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String CustomerId = txtCustomerId.getText();
        String vehicaleId = (String) cmbVehicaleId.getValue();

        try {
            boolean istrue = DeleteOrderModel.deleteAll(CustomerId,vehicaleId);
            if (istrue) {
                new Alert(Alert.AlertType.CONFIRMATION,"delete Successfully").show();
            }
            clearFeleds();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void vehicaleOnAction(ActionEvent event) {
        String id = (String) cmbVehicaleId.getValue();

        try {
            VehicleDto dto = vehicleModel.searchVehicle(id);
            txtModel.setText(dto.getModel());
            txtColor.setText(dto.getColor());
            txtCustomerId.setText(dto.getCustomerId());
        } catch (Exception e) {

        }

    }
    public void clearFeleds(){
        obList.clear();
        cmbService.setValue("");
        cmbVehicaleId.setValue("");
        txtCustomerId.setText("");
        txtModel.setText("");
        txtType.setText("");
        txtColor.setText("");
        txtServiceTotal.setText("");
        txtNetTotal.setText("");
    }

    @FXML
    void btnSendEmailOnAction(ActionEvent event) throws IOException {
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/email_form.fxml")));

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        int focusedIndex = txtPayment.getSelectionModel().getSelectedIndex();

            obList.remove(focusedIndex);
            txtPayment.refresh();
            txtNetTotal.setText("");
        }

    }


