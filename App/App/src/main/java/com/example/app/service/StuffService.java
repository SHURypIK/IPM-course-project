package com.example.app.service;

import com.example.app.SceneManager;
import com.example.app.model.Position;
import com.example.app.model.ResponsiblePerson;
import com.example.app.repositories.PositionRepo;
import com.example.app.repositories.StuffRepo;
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

public class StuffService {


    public static void fill(GridPane pane) {
        ArrayList<String> positions = new ArrayList<>();
        try {
            positions = (ArrayList<String>) PositionRepo.get().stream().map(Position::getName).collect(Collectors.toList());
        } catch (Exception e) {
            showAlert(e.getMessage());
        }

        pane.getChildren().clear();
        pane.setVgap(20);

        pane.add(new Label("ФИО"), 0, 0);
        pane.add(new Label("Телефон"), 1, 0);
        pane.add(new Label("Email"), 2, 0);
        pane.add(new Label("Должности"), 3, 0);
        pane.add(new Label("Изменить"), 4, 0);
        pane.add(new Label("Удалить"), 5, 0);
        pane.add(new Label("Восстановить"), 6, 0);

        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }
        }

        ArrayList<ResponsiblePerson> responsiblePeople = null;
        try {
            responsiblePeople = StuffRepo.get();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
        for (int i = 0; i < responsiblePeople.size(); i++) {
            ResponsiblePerson person = responsiblePeople.get(i);
            int rowIndex = i + 1;

            TextField fioField = new TextField(person.getFio());
            pane.add(fioField, 0, rowIndex);

            TextField phoneField = new TextField(person.getPhone());
            pane.add(phoneField, 1, rowIndex);
            phoneField.setPromptText("Не указан");

            TextField mailField = new TextField(person.getMail());
            pane.add(mailField, 2, rowIndex);
            mailField.setPromptText("Не указан");

            ObservableList<CheckBox> checkBoxList = FXCollections.observableArrayList();
            for (String position : positions) {
                CheckBox checkBox = new CheckBox(position);
                checkBox.setSelected(false);
                checkBoxList.add(checkBox);
            }

            ListView<CheckBox> listView = new ListView<>(checkBoxList);
            listView.setMaxHeight(100);
            listView.setMinHeight(100);
            setSelectedPositions(listView, person.getPositions());
            pane.add(listView, 3, rowIndex);

            Button editButton = new Button("Изменить");
            editButton.setOnAction(e -> {
                try {
                    ResponsiblePerson personForUpdate = new ResponsiblePerson();
                    personForUpdate.setFio(fioField.getText());
                    personForUpdate.setPhone(phoneField.getText());
                    personForUpdate.setMail(mailField.getText());
                    personForUpdate.setPositions(listView.getItems().stream().filter(CheckBox::isSelected).map(CheckBox::getText).collect(Collectors.toList()));
                    update(personForUpdate, person.getFio());
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
                    delete(person);
                    updatePage();
                } catch (Exception ex) {
                    showAlert(ex.getMessage());
                }
            });
            pane.add(deleteButton, 5, rowIndex);

            Button restoreButton = new Button("Восстановить");
            restoreButton.setOnAction(e -> {
                fioField.setText(person.getFio());
                phoneField.setText(person.getPhone());
                mailField.setText(person.getMail());
                setSelectedPositions(listView, person.getPositions());
                editButton.setDisable(true);
                deleteButton.setDisable(false);
            });
            pane.add(restoreButton, 6, rowIndex);


            handleCheckBoxChanges(listView, fioField, phoneField, mailField, person, editButton, deleteButton);


            fioField.textProperty().addListener((obs, oldVal, newVal) -> checkEditButtonState(fioField, phoneField, mailField, listView, person, editButton, deleteButton));
            phoneField.textProperty().addListener((obs, oldVal, newVal) -> checkEditButtonState(fioField, phoneField, mailField, listView, person, editButton, deleteButton));
            mailField.textProperty().addListener((obs, oldVal, newVal) -> checkEditButtonState(fioField, phoneField, mailField, listView, person, editButton, deleteButton));
        }
    }

    private static void checkEditButtonState(TextField fioField, TextField phoneField, TextField mailField, ListView<CheckBox> listView, ResponsiblePerson person, Button editButton, Button deleteButton) {
        // Проверка, что поле "ФИО" не пустое
        boolean isFioNotEmpty = !fioField.getText().isEmpty();

        // Для каждого поля проверяем, если оно null или пустое, считать их одинаковыми
        String currentPhone = phoneField.getText() != null ? phoneField.getText() : "";
        String currentMail = mailField.getText() != null ? mailField.getText() : "";

        // Если person == null, считаем, что ничего не изменено
        boolean isEdited = (person == null ||
                !fioField.getText().equals(person.getFio()) ||
                !currentPhone.equals(person.getPhone() != null ? person.getPhone() : "") ||
                !currentMail.equals(person.getMail() != null ? person.getMail() : "") ||
                !areCheckBoxesChanged(listView, person != null ? person.getPositions() : new ArrayList<>()));

        if (isEdited && isFioNotEmpty) {
            editButton.setDisable(false);
            deleteButton.setDisable(true);
        } else {
            editButton.setDisable(true);
            deleteButton.setDisable(false);
        }
    }
    public static void delete(ResponsiblePerson person) throws Exception {
        StuffRepo.delete(person.getFio());
    }

    public static void save(ResponsiblePerson person) throws Exception {
        StuffRepo.add(person);
        updatePage();
    }

    public static void update(ResponsiblePerson person, String id) throws Exception {
        if(person.getFio().equals(id))
            StuffRepo.update(person, id);
        else
            StuffRepo.updateWithId(person, id);
    }

    private static void updatePage() throws IOException {
        SceneManager.switchScene("stuff.fxml");
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

    public static void setSelectedPositions(ListView<CheckBox> listView, List<String> selectedPositions) {
        for (CheckBox checkBox : listView.getItems()) {
            if (selectedPositions.contains(checkBox.getText())) {
                checkBox.setSelected(true); // Устанавливаем флажок на выбранные должности
            } else {
                checkBox.setSelected(false); // Снимаем флажок с других должностей
            }
        }
    }

    public static boolean areCheckBoxesChanged(ListView<CheckBox> listView, List<String> initialSelectedPositions) {
        Set<String> currentSelectedPositions = listView.getItems().stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.toSet());

        // Преобразуем initialSelectedPositions в Set
        Set<String> initialPositionsSet = new HashSet<>(initialSelectedPositions);

        // Проверяем, что множества равны
        return currentSelectedPositions.equals(initialPositionsSet);
    }

    private static void handleCheckBoxChanges(ListView<CheckBox> listView, TextField fioField, TextField phoneField, TextField mailField, ResponsiblePerson person, Button editButton, Button deleteButton) {
        for (CheckBox checkBox : listView.getItems()) {
            checkBox.setOnAction(event ->
                    checkEditButtonState(fioField, phoneField, mailField, listView, person, editButton, deleteButton)
            );
        }
    }
}
