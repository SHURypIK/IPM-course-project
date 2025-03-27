package com.example.app.service;

import com.example.app.SceneManager;
import com.example.app.model.AdditionalCondition;
import com.example.app.model.Enums.AdditionalConditions;
import com.example.app.model.Position;
import com.example.app.model.ResponsiblePerson;
import com.example.app.repositories.ConditionRepo;
import com.example.app.repositories.PositionRepo;
import com.example.app.repositories.StuffRepo;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConditionService {

    public static void fill(GridPane pane) {
        List<String> places = AdditionalConditions.getTypes();

        pane.getChildren().clear();
        pane.setVgap(20);

        pane.add(new Label("Название"), 0, 0);
        pane.add(new Label("Местоположение"), 1, 0);
        pane.add(new Label("Изменить"), 2, 0);
        pane.add(new Label("Удалить"), 3, 0);
        pane.add(new Label("Восстановить"), 4, 0);

        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }
        }

        ArrayList<AdditionalCondition> conditions = null;
        try {
            conditions = ConditionRepo.get();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
        for (int i = 0; i < conditions.size(); i++) {
            AdditionalCondition condition = conditions.get(i);
            int rowIndex = i + 1;

            TextField nameField = new TextField(condition.getName());
            pane.add(nameField, 0, rowIndex);



            ObservableList<CheckBox> checkBoxList = FXCollections.observableArrayList();
            for (String place : places) {
                CheckBox checkBox = new CheckBox(place);
                checkBox.setSelected(false);
                checkBoxList.add(checkBox);
            }

            ListView<CheckBox> listView = new ListView<>(checkBoxList);
            listView.setMaxHeight(100);
            listView.setMinHeight(100);

            setSelectedPositions(listView, condition.getPlaces().stream().map(AdditionalConditions::getPlace).collect(Collectors.toList()));

            pane.add(listView, 1, rowIndex);

            Button editButton = new Button("Изменить");
            editButton.setOnAction(e -> {
                try {
                    AdditionalCondition conditionForUpdate = new AdditionalCondition();
                    conditionForUpdate.setId(condition.getId());
                    conditionForUpdate.setName(nameField.getText());
                    conditionForUpdate.setPlaces(listView.getItems().stream().filter(CheckBox::isSelected).map(CheckBox::getText).map(AdditionalConditions::fromString).collect(Collectors.toList()));
                    update(conditionForUpdate);
                    updatePage();
                } catch (Exception ex) {
                    showAlert(ex.getMessage());
                }
            });
            editButton.setDisable(true);
            pane.add(editButton, 2, rowIndex);

            Button deleteButton = new Button("Удалить");
            deleteButton.setOnAction(e -> {
                try {
                    delete(condition);
                    updatePage();
                } catch (Exception ex) {
                    showAlert(ex.getMessage());
                }
            });
            pane.add(deleteButton, 3, rowIndex);

            Button restoreButton = new Button("Восстановить");
            restoreButton.setOnAction(e -> {
                nameField.setText(condition.getName());
                setSelectedPositions(listView, condition.getPlaces().stream().map(AdditionalConditions::getPlace).collect(Collectors.toList()));
                editButton.setDisable(true);
                deleteButton.setDisable(false);
            });
            pane.add(restoreButton, 4, rowIndex);


            handleCheckBoxChanges(listView, nameField, condition, editButton, deleteButton);


            nameField.textProperty().addListener((obs, oldVal, newVal) -> checkEditButtonState(nameField, listView, condition, editButton, deleteButton));
        }
    }

    private static void checkEditButtonState(TextField nameField, ListView<CheckBox> listView, AdditionalCondition condition, Button editButton, Button deleteButton) {
        boolean isFioNotEmpty = !nameField.getText().isEmpty();


        boolean isEdited = condition == null ||
                !nameField.getText().equals(condition.getName()) ||
                !areCheckBoxesChanged(listView, condition != null ? condition.getPlaces().stream().map(AdditionalConditions::getPlace).collect(Collectors.toList()) : List.of());

        if (isEdited && isFioNotEmpty) {
            editButton.setDisable(false);
            deleteButton.setDisable(true);
        } else {
            editButton.setDisable(true);
            deleteButton.setDisable(false);
        }
    }

    public static void delete(AdditionalCondition condition) throws Exception {
        ConditionRepo.delete(condition.getId());
    }

    public static void save(AdditionalCondition condition) throws Exception {
        ConditionRepo.add(condition);
        updatePage();
    }

    public static void update(AdditionalCondition condition) throws Exception {
        ConditionRepo.update(condition, condition.getId());

    }

    private static void updatePage() throws IOException {
        SceneManager.switchScene("condition.fxml");
        Stage stage = SceneManager.getPrimaryStage();
        stage.setMaximized(false);
        stage.setMaximized(true);
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.setContentText("Предупреждение закроется автоматически");
        alert.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(7));
        pause.setOnFinished(event -> alert.close());
        pause.play();
    }

    public static void setSelectedPositions(ListView<CheckBox> listView, List<String> selectedPlaces) {
        for (CheckBox checkBox : listView.getItems()) {
            if (selectedPlaces.contains(checkBox.getText())) {
                checkBox.setSelected(true);
            } else {
                checkBox.setSelected(false);
            }
        }
    }

    public static boolean areCheckBoxesChanged(ListView<CheckBox> listView, List<String> initialSelectedPositions) {
        Set<String> currentSelectedPositions = listView.getItems().stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.toSet());

        Set<String> initialPositionsSet = new HashSet<>(initialSelectedPositions);

        return currentSelectedPositions.equals(initialPositionsSet);
    }

    private static void handleCheckBoxChanges(ListView<CheckBox> listView, TextField fioField, AdditionalCondition condition, Button editButton, Button deleteButton) {
        for (CheckBox checkBox : listView.getItems()) {
            checkBox.setOnAction(event ->
                    checkEditButtonState(fioField, listView, condition, editButton, deleteButton)
            );
        }
    }
}
