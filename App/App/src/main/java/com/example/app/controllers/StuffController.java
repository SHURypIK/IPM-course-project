package com.example.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.example.app.SceneManager;
import com.example.app.model.Position;
import com.example.app.model.ResponsiblePerson;
import com.example.app.repositories.PositionRepo;
import com.example.app.service.StuffService;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StuffController {

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
    private TextField fio_field;

    @FXML
    private TextField mail_field;

    @FXML
    private TextField phone_field;

    @FXML
    private GridPane stuff_pane;
    private ListView<CheckBox> listView;



    @FXML
    void initialize() {
        add_button.setDisable(true);

        ArrayList<Position> positions = new ArrayList<>();
        try {
            positions = PositionRepo.get();
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
                ResponsiblePerson person = new ResponsiblePerson();
                person.setFio(fio_field.getText());
                person.setMail(mail_field.getText());
                person.setPhone(phone_field.getText());

                person.setPositions(getSelectedValues());

                StuffService.save(person);
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

        fill();

        ObservableList<CheckBox> checkBoxList = FXCollections.observableArrayList();

        for (String position : positions.stream().map(Position::getName).collect(Collectors.toList())) {
            CheckBox checkBox = new CheckBox(position);
            checkBox.setSelected(false);
            checkBox.setOnAction(event -> checkButtons());

            checkBoxList.add(checkBox);
        }

        listView = new ListView<>(checkBoxList);

        add_pane.add(listView,1,3);


        fio_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        mail_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());
        phone_field.textProperty().addListener((observable, oldValue, newValue) -> checkButtons());


    }

    private void fill(){
        try {
            StuffService.fill(stuff_pane);
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
        return fio_field.getText() != null && !fio_field.getText().isEmpty()
                && mail_field.getText() != null && !mail_field.getText().isEmpty()
                && phone_field.getText() != null && !phone_field.getText().isEmpty();
    }

    private List<CheckBox> getCheckBoxes() {
        return add_pane.getChildren().stream()
                .filter(node -> node instanceof ListView)
                .flatMap(node -> ((ListView<CheckBox>) node).getItems().stream())
                .collect(Collectors.toList());
    }

    public List<String> getSelectedValues() {
        List<String> selectedValues = getCheckBoxes().stream()
                .filter(CheckBox::isSelected)  // Фильтруем только выбранные CheckBox
                .map(CheckBox::getText)        // Получаем текстовое значение выбранного CheckBox
                .collect(Collectors.toList()); // Собираем в список
        return selectedValues;
    }

}
