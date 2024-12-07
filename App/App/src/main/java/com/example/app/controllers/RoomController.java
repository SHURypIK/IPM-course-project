package com.example.app.controllers;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.model.*;
import com.example.app.model.Enums.Gender;
import com.example.app.repositories.ConditionRepo;
import com.example.app.repositories.MoveRepo;
import com.example.app.service.BlockService;
import com.example.app.service.RoomService;
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

public class RoomController {

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
    private ChoiceBox<Integer> block_field;

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

    @FXML
    private TextField number_of_places_field;

    private ListView<CheckBox> listViewCondition;
    private Dormitory dormitory;
    private Floor floor;
    private  Block block;

    @FXML
    void initialize() {

        dormitory = AppState.getInstance().getDormitory();
        floor = AppState.getInstance().getFloor();
        block = AppState.getInstance().getBlock();

        gender_field.setDisable(true);
        add_button.setDisable(true);
        floor_field.setDisable(true);
        block_field.setDisable(true);

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
        ArrayList<Block> blocks = null;
        try {
            blocks = MoveRepo.getBlocks();
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
            conditions = AdditionalCondition.getInRoom(conditions);
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
                AppState.getInstance().setBlock(null);
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
                Room room = new Room();
                room.setNumberOfPlaces(Integer.parseInt(number_of_places_field.getText()));
                room.setNumberOfAvailablePlaces(room.getNumberOfPlaces());
                room.setGender(Gender.fromString(gender_field.getValue()));
                room.setNumber(block.getNumber()*10 + Integer.parseInt(number_field.getText()));
                room.setBlockId(block.getId());
                room.setAdditionalConditions(getSelectedValuesCondition());
                RoomService.save(room);
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

        gender_field.setItems(FXCollections.observableArrayList(Gender.getTexts()));


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
        add_pane.add(listViewCondition,1,3);

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
                block_pane.add(new Label("Количество мест"), 2, 0);
                block_pane.add(new Label("Количество Свободных Мест"), 3, 0);
                block_pane.add(new Label("Дополнительные условия"), 4, 0);
                block_pane.add(new Label("Изменить"), 5, 0);
                block_pane.add(new Label("Удалить"), 6, 0);
                block_pane.add(new Label("Восстановить"), 7, 0);

                for (Node node : block_pane.getChildren()) {
                    if (node instanceof Label) {
                        ((Label) node).setStyle("-fx-font-size: 14px;");
                    }
                }
            } else {
                AppState.getInstance().setDormitory(null);
                dormitory = null;
                floor = null;
                block = null;
                resetComboBox(floor_field, block_field);
            }
        });

        floor_field.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                floor = Floor.findByNumber(dormitory, newValue);
                AppState.getInstance().setFloor(floor);
                block_field.setDisable(false);
                ObservableList<Integer> blockNumbers = FXCollections.observableArrayList(floor.getBlocks().stream().map(Block::getNumber).collect(Collectors.toList()));
                block_field.setItems(blockNumbers);

                block_pane.getChildren().clear();
                block_pane.setVgap(20);

                block_pane.add(new Label("Номер"), 0, 0);
                block_pane.add(new Label("Для кого"), 1, 0);
                block_pane.add(new Label("Количество мест"), 2, 0);
                block_pane.add(new Label("Количество Свободных Мест"), 3, 0);
                block_pane.add(new Label("Дополнительные условия"), 4, 0);
                block_pane.add(new Label("Изменить"), 5, 0);
                block_pane.add(new Label("Удалить"), 6, 0);
                block_pane.add(new Label("Восстановить"), 7, 0);

                for (Node node : block_pane.getChildren()) {
                    if (node instanceof Label) {
                        ((Label) node).setStyle("-fx-font-size: 14px;");
                    }
                }
            } else {
                AppState.getInstance().setDormitory(null);
                floor = null;
                block = null;
                resetComboBox(block_field);
            }
        });

        if(block != null){
            dormitory_field.setValue(dormitory.getNumber());
            dormitory = Dormitory.findByNumber(dormitories, dormitory.getNumber());
            floor = Floor.findByNumber(dormitory, floor.getNumber());
            floor_field.setDisable(false);
            ObservableList<Integer> floorNumbers = FXCollections.observableArrayList(dormitory.getFloors().stream().map(Floor::getNumber).collect(Collectors.toList()));
            floor_field.setItems(floorNumbers);
            floor_field.setValue(floor.getNumber());
            block = Block.findByNumber(floor, block.getNumber());
            block_field.setDisable(false);
            ObservableList<Integer> blockNumbers = FXCollections.observableArrayList(floor.getBlocks().stream().map(Block::getNumber).collect(Collectors.toList()));
            block_field.setItems(blockNumbers);
            block_field.setValue(block.getNumber());
            AppState.getInstance().setDormitory(dormitory);
            AppState.getInstance().setFloor(floor);
            AppState.getInstance().setBlock(block);
            Room room = null;
            if(block.getRooms() != null && !block.getRooms().isEmpty())
                room = block.getRooms().get(0);
            switch (block.getGender()) {
                case MALE -> gender_field.setValue(Gender.MALE.getText());
                case FEMALE -> gender_field.setValue(Gender.FEMALE.getText());
                case NOT_EXIST -> {
                    if (room != null && room.getGender() != null) {
                        gender_field.setValue(room.getGender().getText());
                    } else {
                        gender_field.setDisable(false);
                    }
                }
            }
            fill(block);
        }

        number_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());

        block_field.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkButtons();
            gender_field.setDisable(true);
            if (newValue != null) {
                block = Block.findByNumber(floor, newValue);
                AppState.getInstance().setBlock(block);
                Room room = null;
                if(block.getRooms() != null && !block.getRooms().isEmpty())
                    room = block.getRooms().get(0);
                switch (block.getGender()) {
                    case MALE -> gender_field.setValue(Gender.MALE.getText());
                    case FEMALE -> gender_field.setValue(Gender.FEMALE.getText());
                    case NOT_EXIST -> {
                        if (room != null && room.getGender() != null) {
                            gender_field.setValue(room.getGender().getText());
                        } else {
                            gender_field.setDisable(false);
                        }
                    }
                }
                fill(block);
            } else {
                gender_field.setDisable(true);
                gender_field.setValue(null);
                AppState.getInstance().setDormitory(null);
                block = null;
            }
        });
    }

    private void fill(Block block){
        try {
            RoomService.fill(block_pane, block);
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
                && gender_field.getValue() != null && block_field.getValue() != null;
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
