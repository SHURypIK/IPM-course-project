package com.example.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.app.SceneManager;
import com.example.app.model.Enums.MedicalReportType;
import com.example.app.model.MedicalReport;
import com.example.app.model.Position;
import com.example.app.service.MedicalReportService;
import com.example.app.service.MoveService;
import com.example.app.service.PositionService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PositionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_button;

    @FXML
    private Button back_button;

    @FXML
    private GridPane medical_report_pane;

    @FXML
    private TextField position_field;

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
        position_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!position_field.getText().equals(""))
                add_button.setDisable(false);
            else
                add_button.setDisable(true);
        });

        fill();

        add_button.setOnAction(event -> {
            try {
                Position position = new Position();
                position.setName(position_field.getText());
                PositionService.save(position);
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


    private void fill(){
        try {
            PositionService.fill(medical_report_pane);
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