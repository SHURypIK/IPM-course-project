package com.example.app.controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.example.app.SceneManager;
import com.example.app.model.AdditionalCondition;
import com.example.app.model.Enums.AdditionalConditions;
import com.example.app.model.Position;
import com.example.app.model.ResponsiblePerson;
import com.example.app.service.ConditionService;
import com.example.app.service.StuffService;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConditionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_button;

    @FXML
    private GridPane add_pane;

    @FXML
    private Button back_button;

    @FXML
    private GridPane condition_pane;

    @FXML
    private TextField name_field;

    private ListView<CheckBox> listView;

    @FXML
    void initialize() {
        add_button.setDisable(true);

        List<String> places = AdditionalConditions.getTypes();
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
                AdditionalCondition condition = new AdditionalCondition();
                condition.setName(name_field.getText());
                condition.setPlaces(AdditionalConditions.fromStrings(getSelectedValues()));
                ConditionService.save(condition);
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

        ObservableList<CheckBox> checkBoxList = FXCollections.observableArrayList();

        for (String position : places) {
            CheckBox checkBox = new CheckBox(position);
            checkBox.setSelected(false);
            checkBox.setOnAction(event -> checkButtons());

            checkBoxList.add(checkBox);
        }

        listView = new ListView<>(checkBoxList);

        listView.setMaxHeight(100);
        listView.setMinHeight(100);

        add_pane.add(listView,1,1);


        name_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());

    }


    private void fill(){
        try {
            ConditionService.fill(condition_pane);
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
        return name_field.getText() != null && !name_field.getText().isEmpty();
    }

    private List<CheckBox> getCheckBoxes() {
        return add_pane.getChildren().stream()
                .filter(node -> node instanceof ListView)
                .flatMap(node -> ((ListView<CheckBox>) node).getItems().stream())
                .collect(Collectors.toList());
    }

    public List<String> getSelectedValues() {
        List<String> selectedValues = getCheckBoxes().stream()
                .filter(CheckBox::isSelected)  // Фильтруем только выбранные CheckBox
                .map(CheckBox::getText)        // Получаем текстовое значение выбранного CheckBox
                .collect(Collectors.toList()); // Собираем в список
        return selectedValues;
    }

}
