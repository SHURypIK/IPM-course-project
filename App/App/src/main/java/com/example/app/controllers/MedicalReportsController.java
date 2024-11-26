package com.example.app.controllers;

import com.example.app.SceneManager;
import com.example.app.model.Enums.MedicalReportType;
import com.example.app.model.MedicalReport;
import com.example.app.service.ContractService;
import com.example.app.service.MedicalReportService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MedicalReportsController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_button;

    @FXML
    private Button back_button;

    @FXML
    private DatePicker date_field;

    @FXML
    private TextField doctor_field;

    @FXML
    private CheckBox fir_field;

    @FXML
    private TextField id_field;

    @FXML
    private GridPane medical_report_pane;

    @FXML
    private ChoiceBox<String> type_field;

    @FXML
    void initialize() {
        id_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        doctor_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        date_field.valueProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        type_field.valueProperty().addListener((observable, oldValue, newValue) -> checkButtons());

        back_button.setOnAction(event -> {
            try {
                SceneManager.switchScene("move-resident.fxml");
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

        fill();

        add_button.setDisable(true);
        add_button.setOnAction(event -> {
            try {
                MedicalReport report = new MedicalReport();
                report.setId(id_field.getText());
                report.setFit(fir_field.isSelected());
                report.setDoctor(doctor_field.getText());
                report.setValidUntil(date_field.getValue());
                report.setType(MedicalReportType.fromString(type_field.getValue()));
                MedicalReportService.save(report);
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

    }

    private void checkButtons() {
        add_button.setDisable(!(areAllFieldsFilled()));
    }

    private boolean areAllFieldsFilled() {
       boolean b = id_field.getText() != null && !id_field.getText().isEmpty()
                && doctor_field.getText() != null && !doctor_field.getText().isEmpty()
                && date_field.getValue() != null
                && type_field.getValue() != null && !type_field.getValue().isEmpty();
       return b;
    }

    private void fill(){
        try {
            MedicalReportService.fill(medical_report_pane, type_field);
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

}
