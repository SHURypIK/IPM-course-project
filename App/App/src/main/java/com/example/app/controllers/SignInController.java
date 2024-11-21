package com.example.app.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.app.service.SignInService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enter_button;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField password_text_field;

    @FXML
    private CheckBox show_box;

    @FXML
    void initialize() {
        password_text_field.setVisible(false);
        login_field.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        password_field.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        password_text_field.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        show_box.selectedProperty().addListener((observable, oldValue, newValue) -> togglePasswordVisibility());

        login_field.setOnAction(event -> {
            if(show_box.isSelected())
                password_text_field.requestFocus();
            else
                password_field.requestFocus();
        });

        password_field.setOnAction(event -> enter());
        password_text_field.setOnAction(event -> enter());
        enter_button.setOnAction(event -> enter());
    }

    private void updateButtonState() {
        boolean isLoginFilled = !login_field.getText().isEmpty();
        boolean isPasswordFilled = !password_field.getText().isEmpty() || !password_text_field.getText().isEmpty();
        enter_button.setDisable(!(isLoginFilled && isPasswordFilled));
    }
    private void togglePasswordVisibility() {
        if (show_box.isSelected()) {
            password_field.setVisible(false);
            password_text_field.setVisible(true);
            password_text_field.setText(password_field.getText());
            password_field.setText("");
        } else {
            password_field.setVisible(true);
            password_text_field.setVisible(false);
            password_field.setText(password_text_field.getText());
            password_text_field.setText("");
        }
    }
    public String getNonEmptyField() {
        if (password_text_field.getText().isEmpty()) {
            return password_field.getText();
        } else {
            return password_text_field.getText();
        }
    }

    private void enter(){
        try {
            SignInService.enter(login_field.getText(), getNonEmptyField(), enter_button);
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
