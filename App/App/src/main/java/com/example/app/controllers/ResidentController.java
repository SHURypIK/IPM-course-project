package com.example.app.controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.example.app.SceneManager;
import com.example.app.model.AdditionalCondition;
import com.example.app.model.Enums.AdditionalConditions;
import com.example.app.model.Enums.Gender;
import com.example.app.model.Enums.SettlementBenefit;
import com.example.app.model.Enums.Status;
import com.example.app.model.MedicalReport;
import com.example.app.model.Resident;
import com.example.app.service.ConditionService;
import com.example.app.service.ResidentService;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ResidentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_button;

    @FXML
    private Button back_button;

    @FXML
    private ChoiceBox<String> benefit_field;

    @FXML
    private DatePicker date_field;

    @FXML
    private TextField fio_field;

    @FXML
    private ChoiceBox<String> gender_field;

    @FXML
    private GridPane medical_report_pane;

    @FXML
    void initialize() {
        add_button.setDisable(true);

        back_button.setOnAction(event -> {
            try {
                SceneManager.switchScene("main.fxml");
                Stage stage = SceneManager.getPrimaryStage();
                stage.setMaximized(false);
                stage.setMaximized(true);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(e.getMessage());
                alert.setContentText("Предупреждение закроется автоматически");
                alert.show();

                PauseTransition pause = new PauseTransition(Duration.seconds(7));
                pause.setOnFinished(eventt -> alert.close());
                pause.play();
            }
        });

        gender_field.setItems(FXCollections.observableArrayList(Gender.getTexts()));
        benefit_field.setItems(FXCollections.observableArrayList(SettlementBenefit.getTexts()));

        add_button.setOnAction(event -> {
            try {
                Resident resident = new Resident();
                resident.setFio(fio_field.getText());
                resident.setBirthday(date_field.getValue());
                resident.setGender(Gender.fromString(gender_field.getValue()));
                resident.setBenefit(SettlementBenefit.fromString(benefit_field.getValue()));
                resident.setStatus(Status.EXPECTING);
                ResidentService.save(resident);
            }   catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(e.getMessage());
                alert.setContentText("Предупреждение закроется автоматически");
                alert.show();

                PauseTransition pause = new PauseTransition(Duration.seconds(7));
                pause.setOnFinished(eventt -> alert.close());
                pause.play();
            }
        });

        fill();

        fio_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        date_field.valueProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        gender_field.valueProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        benefit_field.valueProperty().addListener((observable, oldValue, newValue) -> checkButtons());

    }

    private void fill(){
        try {
            ResidentService.fill(medical_report_pane);
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


    private void checkButtons() {
        add_button.setDisable(!areAllFieldsFilled());
    }

    private boolean areAllFieldsFilled() {
        return fio_field.getText() != null && !fio_field.getText().isEmpty()
                && date_field.getValue() != null
                && gender_field.getValue() != null
                && benefit_field.getValue() != null;
    }
}
