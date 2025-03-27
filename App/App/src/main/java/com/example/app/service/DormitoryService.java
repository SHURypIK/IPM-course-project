package com.example.app.service;

import com.example.app.SceneManager;
import com.example.app.model.AdditionalCondition;
import com.example.app.model.Dormitory;
import com.example.app.model.Position;
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
import java.util.*;
import java.util.stream.Collectors;

public class DormitoryService {

    public static void fill(GridPane pane) {
        ArrayList<AdditionalCondition> conditions = new ArrayList<>();
        try {
            conditions = ConditionRepo.get();
            conditions = AdditionalCondition.getInDormitory(conditions);
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
        pane.setHgap(20);
        pane.add(new Label("Номер"), 0, 0);
        pane.add(new Label("Адрес"), 1, 0);
        pane.add(new Label("Дополнительные условия"), 2, 0);
        pane.add(new Label("Персонал"), 3, 0);
        pane.add(new Label("Изменить"), 4, 0);
        pane.add(new Label("Удалить"), 5, 0);
        pane.add(new Label("Восстановить"), 6, 0);

        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }
        }


        ArrayList<Dormitory> dormitories = null;
        try {
            dormitories = MoveRepo.getDormitories();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
        dormitories.sort(Comparator.comparingInt(Dormitory::getNumber));
        for (int i = 0; i < dormitories.size(); i++) {
            Dormitory dormitory = dormitories.get(i);
            int rowIndex = i + 1;

            Label numberLabel = new Label(Integer.toString(dormitory.getNumber()));
            pane.add(numberLabel, 0, rowIndex);


            TextField addressField = new TextField(dormitory.getAddress());
            pane.add(addressField, 1, rowIndex);


            ObservableList<CheckBox> checkBoxListPerson = FXCollections.observableArrayList();
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
            for (String person : personsPosition) {
                CheckBox checkBox = new CheckBox(person);
                checkBox.setSelected(false);
                checkBoxListPerson.add(checkBox);
            }
            ListView<CheckBox> listViewPerson = new ListView<>(checkBoxListPerson);
            listViewPerson.setMaxHeight(100);
            listViewPerson.setMinHeight(100);
            ArrayList<String> values = dormitory.getResponsiblePersons();
            ArrayList<String> selectedValues = new ArrayList<>();
            for(int j =0; j< values.size(); j++){
                String value = values.get(j);
                ResponsiblePerson person = ResponsiblePerson.getByName(persons, value);
                if(person.getPositions() != null && !person.getPositions().isEmpty()){
                    value += ": ";
                    for(String position : person.getPositions()){
                        value += position;
                        value += "-";
                    }
                    value = value.substring(0, value.length()-1);
                }
                selectedValues.add(value);
            }
            setSelected(listViewPerson, selectedValues);
            pane.add(listViewPerson,3,rowIndex);

            ObservableList<CheckBox> checkBoxListCondition = FXCollections.observableArrayList();
            for (String condition : conditions.stream().map(AdditionalCondition::getName).collect(Collectors.toList())) {
                CheckBox checkBox = new CheckBox(condition);
                checkBox.setSelected(false);
                checkBoxListCondition.add(checkBox);
            }
            ListView<CheckBox> listViewCondition = new ListView<>(checkBoxListCondition);
            listViewCondition.setMaxHeight(100);
            listViewCondition.setMinHeight(100);
            setSelected(listViewCondition, dormitory.getAdditionalConditions());
            pane.add(listViewCondition,2,rowIndex);

            Button editButton = new Button("Изменить");
            editButton.setOnAction(e -> {
                try {
                    Dormitory dormitoryForUpdate = new Dormitory();
                    dormitoryForUpdate.setId(dormitory.getId());
                    dormitoryForUpdate.setNumber(dormitory.getNumber());
                    dormitoryForUpdate.setAddress(addressField.getText());
                    dormitoryForUpdate.setResponsiblePersons((ArrayList<String>) getSelectedValuesPerson(listViewPerson));
                    dormitoryForUpdate.setAdditionalConditions(getSelectedValuesCondition(listViewCondition));
                    dormitoryForUpdate.setFloors(dormitory.getFloors());
                    update(dormitoryForUpdate, dormitory.getId());
                    updatePage();
                } catch (Exception ex) {
                    showAlert(ex.getMessage());
                }
            });
            editButton.setDisable(true);
            pane.add(editButton, 4, rowIndex);

            Button deleteButton = new Button("Удалить");
            deleteButton.setOnAction(e -> {
                try {
                    delete(dormitory);
                    updatePage();
                } catch (Exception ex) {
                    showAlert(ex.getMessage());
                }
            });
            pane.add(deleteButton, 5, rowIndex);

            Button restoreButton = new Button("Восстановить");
            ArrayList<ResponsiblePerson> finalPersons = persons;
            restoreButton.setOnAction(e -> {
                addressField.setText(dormitory.getAddress());
                setSelected(listViewCondition, dormitory.getAdditionalConditions());
                setSelected(listViewPerson, selectedValues);
                editButton.setDisable(true);
                deleteButton.setDisable(false);
            });
            pane.add(restoreButton, 6, rowIndex);


            handleCheckBoxChanges(listViewCondition, listViewPerson, addressField, editButton, deleteButton, dormitory);


            addressField.textProperty().addListener((obs, oldVal, newVal) -> checkEditButtonState(listViewCondition, listViewPerson, addressField, editButton, deleteButton, dormitory));
        }
    }

    public static void delete(Dormitory dormitory) throws Exception {
        DormitoryRepo.delete(dormitory.getId());
    }
    public static void save(Dormitory dormitory) throws Exception {
        DormitoryRepo.add(dormitory);
        updatePage();
    }

    public static void update(Dormitory dormitory, int id) throws Exception {
        DormitoryRepo.update(dormitory, id);
    }

    private static void updatePage() throws IOException {
        SceneManager.switchScene("dormitory.fxml");
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

    private static List<CheckBox> getCheckBoxesPerson(ListView<CheckBox> listViewPerson) {
        return listViewPerson.getItems();
    }

    public static List<String> getSelectedValuesPerson(ListView<CheckBox> listViewPerson) {
        List<String> selectedValues = getCheckBoxesPerson(listViewPerson).stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.toList());
        for (int i = 0; i<selectedValues.size(); i++){
            String str = selectedValues.get(i);
            if (str.indexOf(":") != -1) {
                selectedValues.remove(str);
                str = str.substring(0,str.indexOf(":"));
                selectedValues.add(i,str);
            }
        }
        return selectedValues;
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

    private static void handleCheckBoxChanges(ListView<CheckBox> listViewCondition, ListView<CheckBox> listViewPerson, TextField addressField, Button editButton, Button deleteButton, Dormitory dormitory) {
        for (CheckBox checkBox : listViewPerson.getItems()) {
            checkBox.setOnAction(event -> checkEditButtonState(listViewCondition, listViewPerson, addressField, editButton, deleteButton, dormitory));
        }

        for (CheckBox checkBox : listViewCondition.getItems()) {
            checkBox.setOnAction(event -> checkEditButtonState(listViewCondition, listViewPerson, addressField, editButton, deleteButton, dormitory));

        }
    }

    private static void checkEditButtonState(ListView<CheckBox> listViewCondition, ListView<CheckBox> listViewPerson, TextField addressField, Button editButton, Button deleteButton, Dormitory dormitory) {

        boolean isAddressNotEmpty = !addressField.getText().isEmpty();

            String currentAddress = addressField.getText() != null ? addressField.getText() : "";

            boolean isEdited = (dormitory == null ||
                    !currentAddress.equals(dormitory.getAddress() != null ? dormitory.getAddress() : "") ||
                    !areCheckBoxesConditionChanged(listViewCondition, dormitory != null ? dormitory.getAdditionalConditions() : new ArrayList<>()) ||
                    !areCheckBoxesPersonChanged(listViewPerson, dormitory != null ? dormitory.getResponsiblePersons() : new ArrayList<>()));

            if (isEdited && isAddressNotEmpty) {
            editButton.setDisable(false);
            deleteButton.setDisable(true);
        } else {
            editButton.setDisable(true);
            deleteButton.setDisable(false);
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

    public static boolean areCheckBoxesPersonChanged(ListView<CheckBox> listView, List<String> initialSelectedPositions) {
        Set<String> currentSelectedPositions = listView.getItems().stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.toSet());

        ArrayList<ResponsiblePerson> persons = new ArrayList<>();
        try {
            persons = StuffRepo.get();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }

        for(int i = 0; i < initialSelectedPositions.size(); i++){
            String value = initialSelectedPositions.get(i);
            ResponsiblePerson person = ResponsiblePerson.getByName(persons, value);
            if(person.getPositions() != null && person.getPositions().isEmpty()){
                value += ": ";
                for(String position : person.getPositions()){
                    value += position;
                    value += "-";
                }
                value.substring(0, value.length()-1);
            }
        }

        Set<String> initialPositionsSet = new HashSet<>(initialSelectedPositions);

        return currentSelectedPositions.equals(initialPositionsSet);
    }
}
