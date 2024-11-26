package com.example.app.controllers;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.service.MainService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
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
    private MenuItem to_admin;

    @FXML
    private MenuItem to_block;

    @FXML
    private MenuItem to_condition;

    @FXML
    private MenuItem to_dormitory;

    @FXML
    private MenuItem to_floor;

    @FXML
    private MenuItem to_position;

    @FXML
    private MenuItem to_room;

    @FXML
    private MenuItem to_stuff;

    @FXML
    private MenuItem to_user;

    @FXML
    private MenuItem to_resident;


    @FXML
    void initialize() {
        String key = AppState.getInstance().getAccessKey();
        if(AppState.getInstance().getAccessKey() != null){
            menu_bar.setDisable(false);
            to_position.setOnAction(event -> {
                try {
                    SceneManager.switchScene("position.fxml");
                    Stage stage = SceneManager.getPrimaryStage();
                    stage.setMaximized(false);
                    stage.setMaximized(true);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");
                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(eventt -> alert.close());
                    pause.play();
                }

            });

            to_stuff.setOnAction(event -> {
                try {
                    SceneManager.switchScene("stuff.fxml");
                    Stage stage = SceneManager.getPrimaryStage();
                    stage.setMaximized(false);
                    stage.setMaximized(true);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");
                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(eventt -> alert.close());
                    pause.play();
                }

            });

            to_condition.setOnAction(event -> {
                try {
                    SceneManager.switchScene("condition.fxml");
                    Stage stage = SceneManager.getPrimaryStage();
                    stage.setMaximized(false);
                    stage.setMaximized(true);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");
                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(eventt -> alert.close());
                    pause.play();
                }



            });

            to_resident.setOnAction(event -> {
                try {
                    SceneManager.switchScene("resident.fxml");
                    Stage stage = SceneManager.getPrimaryStage();
                    stage.setMaximized(false);
                    stage.setMaximized(true);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");
                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(eventt -> alert.close());
                    pause.play();
                }

            });

            to_user.setOnAction(event -> {
                try {
                    SceneManager.switchScene("user.fxml");
                    Stage stage = SceneManager.getPrimaryStage();
                    stage.setMaximized(false);
                    stage.setMaximized(true);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");
                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(eventt -> alert.close());
                    pause.play();
                }

            });

            to_admin.setOnAction(event -> {
                try {
                    SceneManager.switchScene("admin.fxml");
                    Stage stage = SceneManager.getPrimaryStage();
                    stage.setMaximized(false);
                    stage.setMaximized(true);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");
                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(eventt -> alert.close());
                    pause.play();
                }

            });

            to_dormitory.setOnAction(event -> {
                try {
                    SceneManager.switchScene("dormitory.fxml");
                    Stage stage = SceneManager.getPrimaryStage();
                    stage.setMaximized(false);
                    stage.setMaximized(true);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");
                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(eventt -> alert.close());
                    pause.play();
                }

            });

            to_floor.setOnAction(event -> {
                try {
                    SceneManager.switchScene("floor.fxml");
                    Stage stage = SceneManager.getPrimaryStage();
                    stage.setMaximized(false);
                    stage.setMaximized(true);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");
                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(eventt -> alert.close());
                    pause.play();
                }

            });

            to_block.setOnAction(event -> {
                try {
                    SceneManager.switchScene("block.fxml");
                    Stage stage = SceneManager.getPrimaryStage();
                    stage.setMaximized(false);
                    stage.setMaximized(true);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");
                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(eventt -> alert.close());
                    pause.play();
                }



            });

            to_room.setOnAction(event -> {
                try {
                    SceneManager.switchScene("room.fxml");
                    Stage stage = SceneManager.getPrimaryStage();
                    stage.setMaximized(false);
                    stage.setMaximized(true);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");
                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(eventt -> alert.close());
                    pause.play();
                }



            });

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
