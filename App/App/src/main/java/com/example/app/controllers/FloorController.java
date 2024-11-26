package com.example.app.controllers;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.model.AdditionalCondition;
import com.example.app.model.Dormitory;
import com.example.app.model.Floor;
import com.example.app.model.ResponsiblePerson;
import com.example.app.repositories.ConditionRepo;
import com.example.app.repositories.MainRepo;
import com.example.app.repositories.MoveRepo;
import com.example.app.repositories.StuffRepo;
import com.example.app.service.DormitoryService;
import com.example.app.service.FloorService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FloorController {

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
    private ChoiceBox<Integer> dormitory_field;

    @FXML
    private GridPane floor_pane;

    @FXML
    private TextField number_field;

    @FXML
    private ChoiceBox<String> person_field;

    private ListView<CheckBox> listViewCondition;

    @FXML
    void initialize() {
        Dormitory dorm = AppState.getInstance().getDormitory();

        add_button.setDisable(true);
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
        ArrayList<Dormitory> finalDormitories = dormitories;
        ArrayList<AdditionalCondition> conditions = new ArrayList<>();
        try {
            conditions = ConditionRepo.get();
            conditions = AdditionalCondition.getInFloor(conditions);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Предупреждение закроется автоматически");
            alert.show();

            PauseTransition pause = new PauseTransition(Duration.seconds(7));
            pause.setOnFinished(eventt -> alert.close());
            pause.play();
        }

        ArrayList<ResponsiblePerson> persons = new ArrayList<>();
        try {
            persons = StuffRepo.get();
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
                Floor floor = new Floor();
                floor.setNumber(Integer.parseInt(number_field.getText()));

                floor.setResponsiblePerson(getSelectedValuesPerson(person_field.getValue()));
                floor.setDormitoryId(dormitory_field.getValue());
                floor.setAdditionalConditions(getSelectedValuesCondition());
                FloorService.save(floor);
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
        person_field.setItems(FXCollections.observableArrayList(personsPosition));

        dormitory_field.setItems(FXCollections.observableArrayList(dormitories.stream().map(Dormitory :: getNumber).collect(Collectors.toList())));

        if(dorm != null){
            dormitory_field.setValue(dorm.getNumber());
            fill(Dormitory.findByNumber(dormitories,dorm.getNumber()));
        }

        number_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        dormitory_field.valueProperty().addListener((observable, oldValue, newValue) -> {
            fill(Dormitory.findByNumber(finalDormitories,newValue));
            checkButtons();
        });
    }

    private void fill(Dormitory dormitory){
        try {
            FloorService.fill(floor_pane, dormitory);
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
                && dormitory_field.getValue() != null;
    }
    public String getSelectedValuesPerson(String value) {
        if (value.indexOf(":") != -1) {
            value = value.substring(0,value.indexOf(":"));
        }
        return value;
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
}
