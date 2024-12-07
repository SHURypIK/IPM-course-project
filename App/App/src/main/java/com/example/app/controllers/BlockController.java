package com.example.app.controllers;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.model.*;
import com.example.app.model.Enums.Gender;
import com.example.app.repositories.ConditionRepo;
import com.example.app.repositories.MoveRepo;
import com.example.app.repositories.StuffRepo;
import com.example.app.service.BlockService;
import com.example.app.service.FloorService;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BlockController {

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
    private GridPane block_pane;

    @FXML
    private ChoiceBox<Integer> dormitory_field;

    @FXML
    private ChoiceBox<Integer> floor_field;

    @FXML
    private ChoiceBox<String> gender_field;

    @FXML
    private TextField number_field;

    private ListView<CheckBox> listViewCondition;
    private Dormitory dormitory;
    private Floor floor;


    @FXML
    void initialize() {

        dormitory = AppState.getInstance().getDormitory();
        floor = AppState.getInstance().getFloor();

        add_button.setDisable(true);
        floor_field.setDisable(true);

        ArrayList<Dormitory> dormitories = null;
        try {
            dormitories = MoveRepo.getDormitories();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Предупреждение закроется автоматически");
            alert.show();

            PauseTransition pause = new PauseTransition(Duration.seconds(7));
            pause.setOnFinished(eventt -> alert.close());
            pause.play();
        }
        ArrayList<Floor> floors = null;
        try {
            floors = MoveRepo.getFloors();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Предупреждение закроется автоматически");
            alert.show();

            PauseTransition pause = new PauseTransition(Duration.seconds(7));
            pause.setOnFinished(eventt -> alert.close());
            pause.play();
        }

        ArrayList<AdditionalCondition> conditions = new ArrayList<>();
        try {
            conditions = ConditionRepo.get();
            conditions = AdditionalCondition.getInBlock(conditions);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Предупреждение закроется автоматически");
            alert.show();

            PauseTransition pause = new PauseTransition(Duration.seconds(7));
            pause.setOnFinished(eventt -> alert.close());
            pause.play();
        }

        back_button.setOnAction(event -> {
            try {
                AppState.getInstance().setDormitory(null);
                AppState.getInstance().setFloor(null);
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
                Block block = new Block();
                block.setGender(Gender.fromString(gender_field.getValue()));
                block.setAdditionalConditions(getSelectedValuesCondition());
                block.setNumber(100*floor.getNumber() + Integer.parseInt(number_field.getText()));
                block.setFloorId(floor.getId());
                BlockService.save(block);
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

        gender_field.setItems(FXCollections.observableArrayList(Gender.getFullTexts()));

        ObservableList<CheckBox> checkBoxListCondition = FXCollections.observableArrayList();
        for (String condition : conditions.stream().map(AdditionalCondition::getName).collect(Collectors.toList())) {
            CheckBox checkBox = new CheckBox(condition);
            checkBox.setSelected(false);
            checkBox.setOnAction(event -> checkButtons());

            checkBoxListCondition.add(checkBox);
        }
        listViewCondition = new ListView<>(checkBoxListCondition);
        listViewCondition.setMaxHeight(100);
        listViewCondition.setMinHeight(100);
        add_pane.add(listViewCondition,1,2);

        dormitory_field.setItems(FXCollections.observableArrayList(dormitories.stream().map(Dormitory :: getNumber).collect(Collectors.toList())));
        ArrayList<Dormitory> finalDormitories = dormitories;
        dormitory_field.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                dormitory = Dormitory.findByNumber(finalDormitories, newValue);
                AppState.getInstance().setDormitory(dormitory);
                floor_field.setDisable(false);
                ObservableList<Integer> floorNumbers = FXCollections.observableArrayList(dormitory.getFloors().stream().map(Floor::getNumber).collect(Collectors.toList()));
                floor_field.setItems(floorNumbers);

                block_pane.getChildren().clear();
                block_pane.setVgap(20);

                block_pane.add(new Label("Номер"), 0, 0);
                block_pane.add(new Label("Для кого"), 1, 0);
                block_pane.add(new Label("Дополнительные условия"), 2, 0);
                block_pane.add(new Label("Изменить"), 3, 0);
                block_pane.add(new Label("Удалить"), 4, 0);
                block_pane.add(new Label("Восстановить"), 5, 0);

                for (Node node : block_pane.getChildren()) {
                    if (node instanceof Label) {
                        ((Label) node).setStyle("-fx-font-size: 14px;");
                    }
                }

            } else {
                AppState.getInstance().setDormitory(null);
                dormitory = null;
                floor = null;
                resetComboBox(floor_field);
            }
        });

        if(floor != null){
            dormitory_field.setValue(dormitory.getNumber());
            dormitory = Dormitory.findByNumber(dormitories, dormitory.getNumber());
            floor = Floor.findByNumber(dormitory, floor.getNumber());
            floor_field.setDisable(false);
            ObservableList<Integer> floorNumbers = FXCollections.observableArrayList(dormitory.getFloors().stream().map(Floor::getNumber).collect(Collectors.toList()));
            floor_field.setItems(floorNumbers);
            floor_field.setValue(floor.getNumber());
            AppState.getInstance().setDormitory(dormitory);
            AppState.getInstance().setFloor(floor);
            fill(floor);
        }

        number_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        floor_field.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkButtons();
            if (newValue != null) {
                floor = Floor.findByNumber(dormitory, newValue);
                AppState.getInstance().setFloor(floor);
                fill(floor);
            } else {
                AppState.getInstance().setDormitory(null);
                floor = null;
            }
        });

    }

    private void fill(Floor floor){
        try {
            BlockService.fill(block_pane, floor);
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
        return number_field.getText() != null && !number_field.getText().isEmpty()
                && dormitory_field.getValue() != null &&floor_field.getValue() != null
                && gender_field.getValue() != null;
    }
    private List<CheckBox> getCheckBoxesCondition() {
        return listViewCondition.getItems();
    }
    public List<String> getSelectedValuesCondition() {
        List<String> selectedValues = getCheckBoxesCondition().stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.toList());
        return selectedValues;
    }

    private void resetComboBox(ChoiceBox<?>... comboBoxes) {
        for (ChoiceBox<?> comboBox : comboBoxes) {
            comboBox.getSelectionModel().clearSelection();
            comboBox.setDisable(true);
            comboBox.setItems(FXCollections.observableArrayList());
        }
    }
}
