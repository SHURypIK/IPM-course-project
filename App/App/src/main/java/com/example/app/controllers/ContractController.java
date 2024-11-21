package com.example.app.controllers;

import com.example.app.SceneManager;
import com.example.app.model.LeaseContract;
import com.example.app.service.ContractService;
import com.example.app.service.MoveService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.http.annotation.Contract;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContractController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_button;

    @FXML
    private Button back_button;

    @FXML
    private DatePicker contract_date_field;

    @FXML
    private GridPane contract_pane;

    @FXML
    private Button delete_button;

    @FXML
    private TextField lessee_field;

    @FXML
    private DatePicker until_date_field;

    @FXML
    private Button update_button;

    @FXML
    private Button reset_button;

    private LeaseContract contract;

    @FXML
    void initialize() {

        back_button.setOnAction(event -> {
            try {
                SceneManager.switchScene("move-resident.fxml");
                Stage stage = SceneManager.getPrimaryStage();
                stage.setMaximized(false);
                stage.setMaximized(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fill();
        reset_button.setOnAction(event -> {
            if(contract != null){
                contract_date_field.setValue(contract.getContractDate());
                until_date_field.setValue(contract.getValidUntil());
                lessee_field.setText(contract.getLessee());
                delete_button.setDisable(false);
            } else {
                contract_date_field.setValue(null);
                until_date_field.setValue(null);
                lessee_field.setText(null);
                delete_button.setDisable(true);
            }
            update_button.setDisable(true);
            add_button.setDisable(true);
        });
        lessee_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        contract_date_field.valueProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        until_date_field.valueProperty().addListener((observable, oldValue, newValue) -> checkButtons());

        add_button.setOnAction(event -> {
            LeaseContract newContract = new LeaseContract();
            newContract.setContractDate(contract_date_field.getValue());
            newContract.setValidUntil(until_date_field.getValue());
            newContract.setLessee(lessee_field.getText());
            try {
                contract = ContractService.save(newContract);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            add_button.setDisable(true);
            update_button.setDisable(true);
            delete_button.setDisable(false);
        });

        delete_button.setOnAction(event -> {
            try {
                contract = ContractService.delete(contract);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            contract_date_field.setValue(null);
            until_date_field.setValue(null);
            lessee_field.setText(null);
            delete_button.setDisable(true);
            update_button.setDisable(true);
            add_button.setDisable(true);
        });

        update_button.setOnAction(event -> {
            contract.setContractDate(contract_date_field.getValue());
            contract.setValidUntil(until_date_field.getValue());
            contract.setLessee(lessee_field.getText());
            try {
                contract = ContractService.update(contract);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            add_button.setDisable(true);
            update_button.setDisable(true);
            delete_button.setDisable(false);
        });
    }

    private void checkButtons() {
        boolean valuesDiffer = valuesDifferFromContract();
        update_button.setDisable(!valuesDiffer);
        if (contract != null)
            delete_button.setDisable(valuesDiffer);
        else
            delete_button.setDisable(true);
        add_button.setDisable(!(areAllFieldsFilled() && contract == null));
    }

    private boolean valuesDifferFromContract() {
        if (contract == null) return false;

        boolean lesseeDiffers = !lessee_field.getText().equals(contract.getLessee());
        boolean contractDateDiffers = !contract_date_field.getValue().equals(contract.getContractDate());
        boolean untilDateDiffers = !until_date_field.getValue().equals(contract.getValidUntil());

        return lesseeDiffers || contractDateDiffers || untilDateDiffers;
    }


    private void fill(){
        try {
            contract = ContractService.fill(contract_date_field, until_date_field, lessee_field);
            if(contract != null){
                delete_button.setDisable(false);
                update_button.setDisable(true);
                add_button.setDisable(true);
            } else {
                delete_button.setDisable(true);
                update_button.setDisable(true);
                add_button.setDisable(true);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Предупреждение закроется автоматически");
            alert.show();

            PauseTransition pause = new PauseTransition(Duration.seconds(7));
            pause.setOnFinished(event -> alert.close());
            pause.play();
        }

    }

    private boolean areAllFieldsFilled() {
        return lessee_field.getText() != null && !lessee_field.getText().isEmpty()
                && contract_date_field.getValue() != null
                && until_date_field.getValue() != null;
    }
}
