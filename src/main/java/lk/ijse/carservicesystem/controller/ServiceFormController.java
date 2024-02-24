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
import lk.ijse.carservicesystem.dto.ServiceDto;
import lk.ijse.carservicesystem.dto.tm.ServiceTm;
import lk.ijse.carservicesystem.model.ServiceModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class ServiceFormController {

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDescription1;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ServiceTm> tblService;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtStatus;

    private ServiceModel service = new ServiceModel();

    public void initialize(){
        setCellValueFactory();
        loadAllService();
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFileds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try{
            boolean isDelete = ServiceModel.deleteService(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Service deleted").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private boolean validateService() {
        String serviceId = txtId.getText();
        boolean matches = Pattern.matches("[C][0-9]{3,}",serviceId);

        if(!matches){
            new Alert(Alert.AlertType.ERROR, "Invalid service id.").show();
            return false;
        }
        /*Integer telNum = Integer.valueOf(txtNumber.getText());
        boolean matches1 = Pattern.matches("[0-9]{10}]", telNum);

        if(!matches1){
            new Alert(Alert.AlertType.ERROR, "Invalid customer telephone number.").show();
            return false;
        }*/
        return true;
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidated = validateService();
        if (isValidated) {
            String id = txtId.getText();
            String name = txtName.getText();
            String price = txtPrice.getText();
            String status = txtStatus.getText();
            String description = txtDescription.getText();

            var dto = new ServiceDto(id, name, price, status, description);
            try {
                boolean isSaved = service.saveService(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "service saved").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    @FXML
    void btnSearchOnAction(ActionEvent event) {
        ServiceDto dto = null;
        try {
            dto = service.searchService(txtId.getText());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtPrice.setText(dto.getStatus());
        txtStatus.setText(dto.getStatus());
        txtDescription.setText(dto.getDescription());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String price = txtPrice.getText();
        String status = txtStatus.getText();
        String description = txtDescription.getText();

        var dto = new ServiceDto(id,name,price,status,description);

        try{

            boolean isUpdate = service.updateService(dto);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Service is updated").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void clearFileds() {
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtStatus.setText("");
        txtDescription.setText("");
}
    private void loadAllService() {
        var model = new ServiceModel();

        ObservableList<ServiceTm> obList = FXCollections.observableArrayList();

        try {
            List<ServiceDto> dtoList = service.getAllService();

            for (ServiceDto dto : dtoList) {
                obList.add(
                       new ServiceTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getPrice(),
                                dto.getStatus(),
                                dto.getDescription()

                        )
                );
            }

              tblService.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



