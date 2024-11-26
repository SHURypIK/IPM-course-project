package com.example.app.service;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.model.AdditionalCondition;
import com.example.app.model.Dormitory;
import com.example.app.model.Floor;
import com.example.app.model.ResponsiblePerson;
import com.example.app.repositories.*;
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

public class FloorService {
    public static void fill(GridPane pane, Dormitory dormitory) {

        AppState.getInstance().setDormitory(dormitory);

        ArrayList<AdditionalCondition> conditions = new ArrayList<>();
        try {
            conditions = ConditionRepo.get();
            conditions = AdditionalCondition.getInFloor(conditions);
        } catch (Exception e) {
            showAlert(e.getMessage());
        }

        ArrayList<ResponsiblePerson> persons = new ArrayList<>();
        try {
            persons = StuffRepo.get();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }

        pane.getChildren().clear();
        pane.setVgap(20);

        pane.add(new Label("Номер"), 0, 0);
        pane.add(new Label("Ответственный"), 1, 0);
        pane.add(new Label("Дополнительные условия"), 2, 0);
        pane.add(new Label("Изменить"), 3, 0);
        pane.add(new Label("Удалить"), 4, 0);
        pane.add(new Label("Восстановить"), 5, 0);

        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }
        }


        List<Floor> floors = dormitory.getFloors();

        for (int i = 0; i < floors.size(); i++) {
            Floor floor = floors.get(i);
            int rowIndex = i + 1;

            Label numberLabel = new Label(Integer.toString(floor.getNumber()));
            pane.add(numberLabel, 0, rowIndex);

            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            pane.add(choiceBox, 1, rowIndex);
            ArrayList<String> personsPosition = new ArrayList<>();
            for(ResponsiblePerson person : persons){
                String personString = person.getFio();
                if(person.getPositions() != null && !person.getPositions().isEmpty()){
                    personString += ": ";
                    for(String position : person.getPositions()){
                        personString += position;
                        personString += "-";
                    }
                    personString = personString.substring(0, personString.length()-1);
                }
                personsPosition.add(personString);
            }
            choiceBox.setItems(FXCollections.observableArrayList(personsPosition));
            String value = floor.getResponsiblePerson();
            ResponsiblePerson person = ResponsiblePerson.getByName(persons, value);
            if(!person.getPositions().isEmpty() || person.getPositions() != null){
                value +=": ";
                for(String position : person.getPositions()){
                    value += position;
                    value += "-";
                }
                value = value.substring(0,value.length()-1);
            }
            choiceBox.setValue(value);

            ObservableList<CheckBox> checkBoxListCondition = FXCollections.observableArrayList();
            for (String condition : conditions.stream().map(AdditionalCondition::getName).collect(Collectors.toList())) {
                CheckBox checkBox = new CheckBox(condition);
                checkBox.setSelected(false);
                checkBoxListCondition.add(checkBox);
            }
            ListView<CheckBox> listViewCondition = new ListView<>(checkBoxListCondition);
            listViewCondition.setMaxHeight(100);
            listViewCondition.setMinHeight(100);
            setSelected(listViewCondition, floor.getAdditionalConditions());
            pane.add(listViewCondition,2,rowIndex);

            Button editButton = new Button("Изменить");
            editButton.setOnAction(e -> {
                try {
                    Floor floorForUpdate = new Floor();
                    floorForUpdate.setId(floor.getId());
                    floorForUpdate.setNumber(floor.getNumber());
                    floorForUpdate.setDormitoryId(dormitory.getId());
                    floorForUpdate.setResponsiblePerson(getSelectedValuesPerson(choiceBox.getValue()));
                    floorForUpdate.setAdditionalConditions(getSelectedValuesCondition(listViewCondition));
                    floorForUpdate.setBlocks(floor.getBlocks());
                    update(floorForUpdate, floor.getId());
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
                    delete(floor);
                    updatePage();
                } catch (Exception ex) {
                    showAlert(ex.getMessage());
                }
            });
            pane.add(deleteButton, 4, rowIndex);

            Button restoreButton = new Button("Восстановить");
            ArrayList<ResponsiblePerson> finalPersons = persons;
            String finalValue = value;
            restoreButton.setOnAction(e -> {
                choiceBox.setValue(finalValue);
                setSelected(listViewCondition, floor.getAdditionalConditions());
                editButton.setDisable(true);
                deleteButton.setDisable(false);
            });
            pane.add(restoreButton, 5, rowIndex);


            handleCheckBoxChanges(listViewCondition, choiceBox, editButton, deleteButton, floor);
            choiceBox.valueProperty().addListener((observable, oldValue, newValue) -> checkEditButtonState(listViewCondition, choiceBox,  editButton, deleteButton, floor));
        }
    }

    public static void delete(Floor floor) throws Exception {
        FloorRepo.delete(floor.getId());
    }
    public static void save(Floor floor) throws Exception {
        FloorRepo.add(floor);
        updatePage();
    }
    public static void update(Floor floor, int id) throws Exception {
        FloorRepo.update(floor, id);
    }
    private static void updatePage() throws IOException {
        SceneManager.switchScene("floor.fxml");
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
    public static String getSelectedValuesPerson(String value) {
        if (value.indexOf(":") != -1) {
            value = value.substring(0,value.indexOf(":"));
        }
        return value;
    }
    private static void handleCheckBoxChanges(ListView<CheckBox> listViewCondition, ChoiceBox<String> choiceBox, Button editButton, Button deleteButton, Floor floor) {
        for (CheckBox checkBox : listViewCondition.getItems()) {
            checkBox.setOnAction(event -> checkEditButtonState(listViewCondition, choiceBox, editButton, deleteButton, floor));

        }
    }

    private static void checkEditButtonState(ListView<CheckBox> listViewCondition, ChoiceBox<String> choiceBox, Button editButton, Button deleteButton, Floor floor) {

        boolean isEdited = (floor == null ||
                !areCheckBoxesConditionChanged(listViewCondition, floor != null ? floor.getAdditionalConditions() : new ArrayList<>()) ||
                !getSelectedValuesPerson(choiceBox.getValue()).equals(floor.getResponsiblePerson()));
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
