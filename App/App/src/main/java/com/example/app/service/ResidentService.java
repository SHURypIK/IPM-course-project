package com.example.app.service;

import com.example.app.SceneManager;
import com.example.app.model.AdditionalCondition;
import com.example.app.model.Enums.AdditionalConditions;
import com.example.app.model.Enums.Gender;
import com.example.app.model.Enums.SettlementBenefit;
import com.example.app.model.Resident;
import com.example.app.repositories.ConditionRepo;
import com.example.app.repositories.ResidentRepo;
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

public class ResidentService {

    public static void fill(GridPane pane) {

        pane.getChildren().clear();
        pane.setVgap(20);

        pane.add(new Label("ФИО"), 0, 0);
        pane.add(new Label("Пол"), 1, 0);
        pane.add(new Label("День Рождения"), 2, 0);
        pane.add(new Label("Статус"), 3, 0);
        pane.add(new Label("Льгота"), 4, 0);
        pane.add(new Label("Изменить"), 5, 0);
        pane.add(new Label("Удалить"), 6, 0);
        pane.add(new Label("Восстановить"), 7, 0);

        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }
        }

        ArrayList<Resident> residents = null;
        try {
            residents = ResidentRepo.get();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
        for (int i = 0; i < residents.size(); i++) {
            Resident resident = residents.get(i);
            int rowIndex = i + 1;

            TextField nameField = new TextField(resident.getFio());
            pane.add(nameField, 0, rowIndex);

            ChoiceBox<String> genderBox = new ChoiceBox<>();
            genderBox.setItems(FXCollections.observableArrayList(Gender.getTexts()));
            genderBox.setValue(resident.getGender().getText());
            pane.add(genderBox, 1, rowIndex);

            DatePicker datePicker = new DatePicker(resident.getBirthday());
            pane.add(datePicker, 2, rowIndex);

            Label status = new Label(resident.getStatus().getText());
            pane.add(status, 3, rowIndex);

            ChoiceBox<String> benefitBox = new ChoiceBox<>();
            benefitBox.setItems(FXCollections.observableArrayList(SettlementBenefit.getTexts()));
            benefitBox.setValue(resident.getBenefit().getText());
            pane.add(benefitBox, 4, rowIndex);

            Button editButton = new Button("Изменить");
            editButton.setOnAction(e -> {
                try {
                    Resident residentForUpdate = new Resident();
                    residentForUpdate.setFio(nameField.getText());
                    residentForUpdate.setStatus(resident.getStatus());
                    residentForUpdate.setBenefit(SettlementBenefit.fromString(benefitBox.getValue()));
                    residentForUpdate.setGender(Gender.fromString(genderBox.getValue()));
                    residentForUpdate.setBirthday(datePicker.getValue());
                    residentForUpdate.setLeaseContract(resident.getLeaseContract());
                    residentForUpdate.setMedicalReports(resident.getMedicalReports());
                    update(residentForUpdate, resident.getFio());
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
                    delete(resident);
                    updatePage();
                } catch (Exception ex) {
                    showAlert(ex.getMessage());
                }
            });
            pane.add(deleteButton, 6, rowIndex);

            Button restoreButton = new Button("Восстановить");
            restoreButton.setOnAction(e -> {
                nameField.setText(resident.getFio());
                genderBox.setValue(resident.getGender().getText());
                datePicker.setValue(resident.getBirthday());
                benefitBox.setValue(resident.getBenefit().getText());
                editButton.setDisable(true);
                deleteButton.setDisable(false);
            });
            pane.add(restoreButton, 7, rowIndex);


            addChangeListeners(nameField, genderBox, datePicker, benefitBox, resident, editButton, deleteButton);
        }
    }

    private static void checkEditButtonState(TextField nameField, ChoiceBox<String> genderBox, DatePicker datePicker,
                                             ChoiceBox<String> benefitBox, Resident resident, Button editButton, Button deleteButton) {
        boolean isNameChanged = !nameField.getText().equals(resident.getFio());
        boolean isGenderChanged = !genderBox.getValue().equals(resident.getGender().getText());
        boolean isDateChanged = !datePicker.getValue().equals(resident.getBirthday());
        boolean isBenefitChanged = !benefitBox.getValue().equals(resident.getBenefit().getText());

        boolean isEdited = isNameChanged || isGenderChanged || isDateChanged || isBenefitChanged;

        editButton.setDisable(!isEdited); // Кнопка "Изменить" активна только при изменении
        deleteButton.setDisable(isEdited); // Кнопка "Удалить" отключается, если есть изменения
    }

    public static void delete(Resident resident) throws Exception {
        ResidentRepo.delete(resident.getFio());
    }

    public static void save(Resident resident) throws Exception {
        ResidentRepo.add(resident);
        updatePage();
    }

    public static void update(Resident resident, String id) throws Exception {
        if (resident.getFio().equals(id))
            ResidentRepo.update(resident, id);
        else
            ResidentRepo.updateWithId(resident, id);
    }

    private static void updatePage() throws IOException {
        SceneManager.switchScene("resident.fxml");
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

    private static void addChangeListeners(TextField nameField, ChoiceBox<String> genderBox, DatePicker datePicker,
                                           ChoiceBox<String> benefitBox, Resident resident, Button editButton, Button deleteButton) {
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            checkEditButtonState(nameField, genderBox, datePicker, benefitBox, resident, editButton, deleteButton);
        });

        genderBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkEditButtonState(nameField, genderBox, datePicker, benefitBox, resident, editButton, deleteButton);
        });

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkEditButtonState(nameField, genderBox, datePicker, benefitBox, resident, editButton, deleteButton);
        });

        benefitBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkEditButtonState(nameField, genderBox, datePicker, benefitBox, resident, editButton, deleteButton);
        });
    }
}