package com.example.app.controllers;

import com.example.app.SceneManager;
import com.example.app.model.Admin;
import com.example.app.model.ResponsiblePerson;
import com.example.app.model.User;
import com.example.app.service.AdminService;
import com.example.app.service.StuffService;
import com.example.app.service.UserService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_button;

    @FXML
    private Button back_button;

    @FXML
    private TextField login_field;

    @FXML
    private GridPane medical_report_pane;

    @FXML
    private TextField password_field;

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

        add_button.setOnAction(event -> {
            try {
                Admin admin = new Admin();
                admin.setLogin(login_field.getText());
                admin.setPassword(password_field.getText());
                AdminService.save(admin);
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

        login_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        password_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());

    }


    private void fill(){
        try {
            AdminService.fill(medical_report_pane);
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
        return login_field.getText() != null && !login_field.getText().isEmpty()
                && password_field.getText() != null && !password_field.getText().isEmpty();
    }
}
