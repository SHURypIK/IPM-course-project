package com.example.app.controllers;

import com.example.app.AppState;
import com.example.app.service.MainService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menu_bar;

    @FXML
    private Pagination pagination_element;

    @FXML
    private GridPane grid_pane;


    @FXML
    void initialize() {
        String key = AppState.getInstance().getAccessKey();
        if(AppState.getInstance().getAccessKey() != null){
            menu_bar.setDisable(false);
        } else {
            menu_bar.setDisable(true);
        }
        fill(grid_pane, pagination_element);


    }

    private void fill(GridPane grid_pane, Pagination pagination){
        try {
            MainService.fill(grid_pane, pagination);
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
