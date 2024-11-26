package com.example.app.service;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.model.*;
import com.example.app.model.Enums.Gender;
import com.example.app.repositories.BlockRepo;
import com.example.app.repositories.ConditionRepo;
import com.example.app.repositories.RoomRepo;
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

public class RoomService {

    public static void fill(GridPane pane, Block block) {

        AppState.getInstance().setBlock(block);
        ArrayList<AdditionalCondition> conditions = new ArrayList<>();
        try {
            conditions = ConditionRepo.get();
            conditions = AdditionalCondition.getInRoom(conditions);
        } catch (Exception e) {
            showAlert(e.getMessage());
        }

        pane.getChildren().clear();
        pane.setVgap(20);

        pane.add(new Label("Номер"), 0, 0);
        pane.add(new Label("Для кого"), 1, 0);
        pane.add(new Label("Количество мест"), 2, 0);
        pane.add(new Label("Количество Свободных Мест"), 3, 0);
        pane.add(new Label("Дополнительные условия"), 4, 0);
        pane.add(new Label("Изменить"), 5, 0);
        pane.add(new Label("Удалить"), 6, 0);
        pane.add(new Label("Восстановить"), 7, 0);

        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }
        }


        List<Room> rooms = block.getRooms();

        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            int rowIndex = i + 1;

            Label numberLabel = new Label(Integer.toString(room.getNumber()));
            pane.add(numberLabel, 0, rowIndex);

            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            pane.add(choiceBox, 1, rowIndex);

            choiceBox.setItems(FXCollections.observableArrayList(Gender.getTexts()));
            List<Resident> residents = room.getResidents();
            if(!residents.isEmpty() || !block.getGender().equals(Gender.NOT_EXIST))
                choiceBox.setDisable(true);
            choiceBox.setValue(block.getGender().getText());

            TextField place_field = new TextField(Integer.toString(room.getNumberOfPlaces()));
            pane.add(place_field,2,rowIndex);

            Label label = new Label(Integer.toString(room.getNumberOfAvailablePlaces()));
            pane.add(label,3,rowIndex);


            ObservableList<CheckBox> checkBoxListCondition = FXCollections.observableArrayList();
            for (String condition : conditions.stream().map(AdditionalCondition::getName).collect(Collectors.toList())) {
                CheckBox checkBox = new CheckBox(condition);
                checkBox.setSelected(false);
                checkBoxListCondition.add(checkBox);
            }
            ListView<CheckBox> listViewCondition = new ListView<>(checkBoxListCondition);
            listViewCondition.setMaxHeight(100);
            listViewCondition.setMinHeight(100);
            setSelected(listViewCondition, room.getAdditionalConditions());
            pane.add(listViewCondition,4,rowIndex);



            Button editButton = new Button("Изменить");
            editButton.setOnAction(e -> {
                try {
                    Room roomForUpdate = new Room();
                    roomForUpdate.setId(room.getId());
                    roomForUpdate.setNumber(room.getNumber());
                    roomForUpdate.setNumberOfPlaces(Integer.parseInt(place_field.getText()));
                    roomForUpdate.setNumberOfAvailablePlaces(-room.getNumberOfPlaces() + roomForUpdate.getNumberOfPlaces() + room.getNumberOfAvailablePlaces());
                    if(roomForUpdate.getNumberOfAvailablePlaces() < 1)
                        throw new RuntimeException("Количество мест меньше допустимого");
                    roomForUpdate.setResidents(room.getResidents());
                    roomForUpdate.setGender(Gender.fromString(choiceBox.getValue()));
                    roomForUpdate.setAdditionalConditions(getSelectedValuesCondition(listViewCondition));
                    roomForUpdate.setBlockId(block.getId());
                    update(roomForUpdate, room.getId());
                    updatePage();
                } catch (Exception ex) {
                    showAlert(ex.getMessage());
                }
            });
            editButton.setDisable(true);
            pane.add(editButton, 5, rowIndex);

            Button deleteButton = new Button("Удалить");
            deleteButton.setOnAction(e -> {
                try {
                    delete(room);
                    updatePage();
                } catch (Exception ex) {
                    showAlert(ex.getMessage());
                }
            });
            pane.add(deleteButton, 6, rowIndex);

            Button restoreButton = new Button("Восстановить");
            restoreButton.setOnAction(e -> {
                choiceBox.setValue(room.getGender().getText());
                setSelected(listViewCondition, room.getAdditionalConditions());
                place_field.setText(Integer.toString(room.getNumberOfPlaces()));
                editButton.setDisable(true);
                deleteButton.setDisable(false);
            });
            pane.add(restoreButton, 7, rowIndex);


            handleCheckBoxChanges(listViewCondition, choiceBox,place_field, editButton, deleteButton, room);
            choiceBox.valueProperty().addListener((observable, oldValue, newValue) -> checkEditButtonState(listViewCondition, choiceBox,place_field,  editButton, deleteButton, room));
            place_field.textProperty().addListener((observable, oldValue, newValue) -> checkEditButtonState(listViewCondition, choiceBox,place_field,  editButton, deleteButton, room));


        }
    }

    public static void delete(Room room) throws Exception {
        RoomRepo.delete(room.getId());
    }
    public static void save(Room room) throws Exception {
        RoomRepo.add(room);
        updatePage();
    }
    public static void update(Room room, int id) throws Exception {
        RoomRepo.update(room, id);
    }
    private static void updatePage() throws IOException {
        SceneManager.switchScene("room.fxml");
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



    private static void handleCheckBoxChanges(ListView<CheckBox> listViewCondition, ChoiceBox<String> choiceBox,TextField textField, Button editButton, Button deleteButton, Room room) {
        for (CheckBox checkBox : listViewCondition.getItems()) {
            checkBox.setOnAction(event -> checkEditButtonState(listViewCondition, choiceBox,textField, editButton, deleteButton, room));

        }
    }
    private static void checkEditButtonState(ListView<CheckBox> listViewCondition, ChoiceBox<String> choiceBox,TextField textField, Button editButton, Button deleteButton, Room room) {

        boolean isEdited = (room == null ||
                !areCheckBoxesConditionChanged(listViewCondition, room != null ? room.getAdditionalConditions() : new ArrayList<>()) ||
                !choiceBox.getValue().equals(room.getGender().getText())
                || Integer.parseInt(textField.getText()) != room.getNumberOfPlaces()
        );
        if (isEdited) {
            if(Integer.parseInt(textField.getText()) < room.getNumberOfPlaces() - room.getNumberOfAvailablePlaces())
                editButton.setDisable(true);
            else
                editButton.setDisable(false);
            deleteButton.setDisable(true);
        } else {
            editButton.setDisable(true);
            deleteButton.setDisable(false);
        }
    }




    private static List<CheckBox> getCheckBoxesCondition(ListView<CheckBox> listViewCondition) {
        return listViewCondition.getItems();
    }
    private static List<String> getSelectedValuesCondition(ListView<CheckBox> listViewCondition) {
        List<String> selectedValues = getCheckBoxesCondition(listViewCondition).stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.toList());
        return selectedValues;
    }
    private static void setSelected(ListView<CheckBox> listView, List<String> selectedPositions) {
        for (CheckBox checkBox : listView.getItems()) {
            if (selectedPositions.contains(checkBox.getText())) {
                checkBox.setSelected(true); // Устанавливаем флажок на выбранные должности
            } else {
                checkBox.setSelected(false); // Снимаем флажок с других должностей
            }
        }
    }
    private static boolean areCheckBoxesConditionChanged(ListView<CheckBox> listView, List<String> initialSelectedPositions) {
        Set<String> currentSelectedPositions = listView.getItems().stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.toSet());

        Set<String> initialPositionsSet = new HashSet<>(initialSelectedPositions);

        return currentSelectedPositions.equals(initialPositionsSet);
    }


}
