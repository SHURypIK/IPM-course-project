package com.example.app.controllers;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.model.Block;
import com.example.app.model.Dormitory;
import com.example.app.model.Enums.Gender;
import com.example.app.model.Enums.Status;
import com.example.app.model.Floor;
import com.example.app.model.Room;
import com.example.app.service.MoveService;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MoveController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;

    @FXML
    private Label benefit_label;

    @FXML
    private Label birthday_label;

    @FXML
    private ChoiceBox<Integer> block_box;

    @FXML
    private Button contract_button;

    @FXML
    private Label contract_date_label;

    @FXML
    private GridPane contract_pane;

    @FXML
    private Label contract_until_date_label;

    @FXML
    private ChoiceBox<Integer> dormitory_box;

    @FXML
    private Label fio_label;

    @FXML
    private ChoiceBox<Integer> floor_box;

    @FXML
    private Label gender_label;

    @FXML
    private Label lessee_label;

    @FXML
    private GridPane medical_report_pane;

    @FXML
    private Button medicl_report_button;

    @FXML
    private Button move_button;

    @FXML
    private GridPane move_pane;

    @FXML
    private GridPane resident_pane;

    @FXML
    private ChoiceBox<Integer> room_box;

    @FXML
    private Label status_label;

    @FXML
    private Label tenat_label;

    private Dormitory dormitory;
    private Floor floor;
    private Block block;
    private Room room;

    @FXML
    void initialize() {
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
        fill();

        if(AppState.getInstance().getResidentShort().getStatus() != Status.SETTLED) {
            move_button.setDisable(true);
            room_box.setDisable(true);
            block_box.setDisable(true);
            floor_box.setDisable(true);
            ObservableList<Integer> dormitoryNumbers = FXCollections.observableArrayList();
            for(Dormitory dormitory : AppState.getInstance().getDormitories()){
                dormitoryNumbers.add(dormitory.getNumber());
            }
            dormitory_box.setItems(dormitoryNumbers);
            dormitory_box.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    dormitory = AppState.getInstance().findByNumber(newValue);
                    floor_box.setDisable(false);
                    ObservableList<Integer> floorNumbers = FXCollections.observableArrayList(dormitory.getFloors().stream().map(Floor::getNumber).collect(Collectors.toList()));
                    floor_box.setItems(floorNumbers);
                } else {
                    dormitory = null;
                    floor = null;
                    block = null;
                    room = null;
                    resetComboBox(floor_box, block_box, room_box);
                }
            });

            floor_box.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    floor = AppState.getInstance().findByNumber(dormitory, newValue);
                    block_box.setDisable(false);
                    ObservableList<Integer> blockNumbers = FXCollections.observableArrayList(floor.getBlocks().stream().filter(block ->
                            block.getGender() == AppState.getInstance().getResidentShort().getGender() || block.getGender() == Gender.NOT_EXIST).map(Block::getNumber).collect(Collectors.toList()));
                    block_box.setItems(blockNumbers);
                } else {
                    floor = null;
                    block = null;
                    room = null;
                    resetComboBox(block_box, room_box);
                }
            });

            block_box.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    block = AppState.getInstance().findByNumber(floor, newValue);
                    room_box.setDisable(false);
                    ObservableList<Integer> roomNumbers = FXCollections.observableArrayList(block.getRooms().stream().filter(room ->
                            (room.getGender() == AppState.getInstance().getResidentShort().getGender() || room.getGender() == Gender.NOT_EXIST) &&
                                    room.getNumberOfAvailablePlaces() > 0).map(Room::getNumber).collect(Collectors.toList()));
                    room_box.setItems(roomNumbers);
                } else {
                    block = null;
                    room = null;
                    resetComboBox(room_box);
                }
            });

            room_box.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    room = AppState.getInstance().findByNumber(block, newValue);
                    move_button.setDisable(false);
                }
                else {
                    room = null;
                }
            });
        } else{
            room = AppState.getInstance().findByResident();
            block = AppState.getInstance().findByRoom(room.getId());
            floor = AppState.getInstance().findByBlock(block.getId());
            dormitory = AppState.getInstance().findByFloor(floor.getId());
            move_button.setText("Выселить");
            dormitory_box.setValue(dormitory.getNumber());
            floor_box.setValue(floor.getNumber());
            block_box.setValue(block.getNumber());
            room_box.setValue(room.getNumber());
            dormitory_box.setDisable(true);
            floor_box.setDisable(true);
            block_box.setDisable(true);
            room_box.setDisable(true);
        }
        move_button.setOnAction(event -> {
            try {
                MoveService.move(room);
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

        contract_button.setOnAction(event -> {
            try {
                SceneManager.switchScene("contract.fxml");
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



    }

    private void fill(){
        try {
            MoveService.fill(resident_pane, contract_pane, medical_report_pane);
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

    private void resetComboBox(ChoiceBox<?>... comboBoxes) {
        for (ChoiceBox<?> comboBox : comboBoxes) {
            comboBox.getSelectionModel().clearSelection();
            comboBox.setDisable(true);
            comboBox.setItems(FXCollections.observableArrayList());
        }
        move_button.setDisable(true);
    }
}
