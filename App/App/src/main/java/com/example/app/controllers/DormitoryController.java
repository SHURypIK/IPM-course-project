package com.example.app.controllers;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.model.AdditionalCondition;
import com.example.app.model.Dormitory;
import com.example.app.model.Position;
import com.example.app.model.ResponsiblePerson;
import com.example.app.repositories.ConditionRepo;
import com.example.app.repositories.DormitoryRepo;
import com.example.app.repositories.PositionRepo;
import com.example.app.repositories.StuffRepo;
import com.example.app.service.DormitoryService;
import com.example.app.service.StuffService;
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

public class DormitoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_button;

    @FXML
    private GridPane add_pane;

    @FXML
    private TextField address_field;

    @FXML
    private Button back_button;

    @FXML
    private GridPane dormitory_pane;

    @FXML
    private TextField number_field;

    private ListView<CheckBox> listViewPerson;

    private ListView<CheckBox> listViewCondition;

    @FXML
    void initialize() {
        add_button.setDisable(true);

        ArrayList<AdditionalCondition> conditions = new ArrayList<>();
        try {
             conditions = ConditionRepo.get();
             conditions = AdditionalCondition.getInDormitory(conditions);
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
                Dormitory dormitory = new Dormitory();
                dormitory.setAddress(address_field.getText());
                dormitory.setNumber(Integer.parseInt(number_field.getText()));
                dormitory.setAdditionalConditions(getSelectedValuesCondition());
                dormitory.setResponsiblePersons((ArrayList<String>) getSelectedValuesPerson());
                DormitoryService.save(dormitory);
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
            checkBox.setOnAction(event -> checkButtons());

            checkBoxListPerson.add(checkBox);
        }
        listViewPerson = new ListView<>(checkBoxListPerson);
        listViewPerson.setMaxHeight(100);
        listViewPerson.setMinHeight(100);
        add_pane.add(listViewPerson,1,3);


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



        number_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        address_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        fill();
    }


    private void fill(){
        try {
            DormitoryService.fill(dormitory_pane);
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
                && address_field.getText() != null && !address_field.getText().isEmpty();
    }


    private List<CheckBox> getCheckBoxesPerson() {
        return listViewPerson.getItems();
    }

    public List<String> getSelectedValuesPerson() {
        List<String> selectedValues = getCheckBoxesPerson().stream()
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
