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

public class BlockService {

    public static void fill(GridPane pane, Floor floor) {

        AppState.getInstance().setFloor(floor);

        ArrayList<AdditionalCondition> conditions = new ArrayList<>();
        try {
            conditions = ConditionRepo.get();
            conditions = AdditionalCondition.getInBlock(conditions);
        } catch (Exception e) {
            showAlert(e.getMessage());
        }

        pane.getChildren().clear();
        pane.setVgap(20);

        pane.add(new Label("Номер"), 0, 0);
        pane.add(new Label("Для кого"), 1, 0);
        pane.add(new Label("Дополнительные условия"), 2, 0);
        pane.add(new Label("Изменить"), 3, 0);
        pane.add(new Label("Удалить"), 4, 0);
        pane.add(new Label("Восстановить"), 5, 0);

        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }
        }


        List<Block> blocks = floor.getBlocks();

        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            int rowIndex = i + 1;

            Label numberLabel = new Label(Integer.toString(block.getNumber()));
            pane.add(numberLabel, 0, rowIndex);

            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            pane.add(choiceBox, 1, rowIndex);

            choiceBox.setItems(FXCollections.observableArrayList(Gender.getFullTexts()));
            List<Resident> residents = new ArrayList<>();
            for(Room room : block.getRooms()){
                residents.addAll(room.getResidents());
            }
            if(!residents.isEmpty())
                choiceBox.setDisable(true);

            choiceBox.setValue(block.getGender().getText());

            ObservableList<CheckBox> checkBoxListCondition = FXCollections.observableArrayList();
            for (String condition : conditions.stream().map(AdditionalCondition::getName).collect(Collectors.toList())) {
                CheckBox checkBox = new CheckBox(condition);
                checkBox.setSelected(false);
                checkBoxListCondition.add(checkBox);
            }
            ListView<CheckBox> listViewCondition = new ListView<>(checkBoxListCondition);
            listViewCondition.setMaxHeight(100);
            listViewCondition.setMinHeight(100);
            setSelected(listViewCondition, block.getAdditionalConditions());
            pane.add(listViewCondition,2,rowIndex);

            Button editButton = new Button("Изменить");
            editButton.setOnAction(e -> {
                try {
                    Block blockForUpdate = new Block();
                    blockForUpdate.setFloorId(floor.getId());
                    blockForUpdate.setNumber(block.getNumber());
                    blockForUpdate.setAdditionalConditions(getSelectedValuesCondition(listViewCondition));
                    blockForUpdate.setGender(Gender.fromString(choiceBox.getValue()));
                    blockForUpdate.setRooms(block.getRooms());
                    blockForUpdate.setId(block.getId());
                    update(blockForUpdate, block.getId());
                    if(!blockForUpdate.getGender().equals(block.getGender()))
                        if(!blockForUpdate.getGender().equals(Gender.NOT_EXIST))
                            for(Room room : block.getRooms()){
                                room.setGender(blockForUpdate.getGender());
                                RoomRepo.update(room, room.getId());
                            }
                    updatePage();
                } catch (Exception ex) {
                    showAlert(ex.getMessage());
                }
            });
            editButton.setDisable(true);
            pane.add(editButton, 3, rowIndex);

            Button deleteButton = new Button("Удалить");
            deleteButton.setOnAction(e -> {
                try {
                    delete(block);
                    updatePage();
                } catch (Exception ex) {
                    showAlert(ex.getMessage());
                }
            });
            pane.add(deleteButton, 4, rowIndex);

            Button restoreButton = new Button("Восстановить");
            restoreButton.setOnAction(e -> {
                choiceBox.setValue(block.getGender().getText());
                setSelected(listViewCondition, block.getAdditionalConditions());
                editButton.setDisable(true);
                deleteButton.setDisable(false);
            });
            pane.add(restoreButton, 5, rowIndex);


            handleCheckBoxChanges(listViewCondition, choiceBox, editButton, deleteButton, block);
            choiceBox.valueProperty().addListener((observable, oldValue, newValue) -> checkEditButtonState(listViewCondition, choiceBox,  editButton, deleteButton, block));
        }
    }

    public static void delete(Block block) throws Exception {
        BlockRepo.delete(block.getId());
    }
    public static void save(Block block) throws Exception {
        BlockRepo.add(block);
        updatePage();
    }
    public static void update(Block block, int id) throws Exception {
        BlockRepo.update(block, id);
    }
    private static void updatePage() throws IOException {
        SceneManager.switchScene("block.fxml");
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



    private static void handleCheckBoxChanges(ListView<CheckBox> listViewCondition, ChoiceBox<String> choiceBox, Button editButton, Button deleteButton, Block block) {
        for (CheckBox checkBox : listViewCondition.getItems()) {
            checkBox.setOnAction(event -> checkEditButtonState(listViewCondition, choiceBox, editButton, deleteButton, block));

        }
    }

    private static void checkEditButtonState(ListView<CheckBox> listViewCondition, ChoiceBox<String> choiceBox, Button editButton, Button deleteButton, Block block) {

        boolean isEdited = (block == null ||
                !areCheckBoxesConditionChanged(listViewCondition, block != null ? block.getAdditionalConditions() : new ArrayList<>()) ||
                !choiceBox.getValue().equals(block.getGender().getText())) ;
        if (isEdited) {
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

    public static List<String> getSelectedValuesCondition(ListView<CheckBox> listViewCondition) {
        List<String> selectedValues = getCheckBoxesCondition(listViewCondition).stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.toList());
        return selectedValues;
    }

    public static void setSelected(ListView<CheckBox> listView, List<String> selectedPositions) {
        for (CheckBox checkBox : listView.getItems()) {
            if (selectedPositions.contains(checkBox.getText())) {
                checkBox.setSelected(true); // Устанавливаем флажок на выбранные должности
            } else {
                checkBox.setSelected(false); // Снимаем флажок с других должностей
            }
        }
    }

    public static boolean areCheckBoxesConditionChanged(ListView<CheckBox> listView, List<String> initialSelectedPositions) {
        Set<String> currentSelectedPositions = listView.getItems().stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.toSet());

        Set<String> initialPositionsSet = new HashSet<>(initialSelectedPositions);

        return currentSelectedPositions.equals(initialPositionsSet);
    }

}
