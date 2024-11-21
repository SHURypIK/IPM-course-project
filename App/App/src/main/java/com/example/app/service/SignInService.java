package com.example.app.service;

import com.example.app.AppState;
import com.example.app.HelloApplication;
import com.example.app.SceneManager;
import com.example.app.model.Admin;
import com.example.app.model.User;
import com.example.app.repositories.SignInRepo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SignInService {


    public static void enter(String login, String password, Button button) throws Exception {
        User user = SignInRepo.enterUser(login, password);
        Admin admin = null;
        try {
            admin = SignInRepo.enterAdmin(login, password);
            AppState.getInstance().setAccessKey(admin.getAccessKey());
        } catch (Exception e){
            AppState.getInstance().setAccessKey(null);
        }
        AppState.getInstance().setUser(user);

        SceneManager.switchScene("main.fxml");

        Stage stage = SceneManager.getPrimaryStage();
        stage.setMaximized(true);
        stage.setResizable(true);
    }
}
