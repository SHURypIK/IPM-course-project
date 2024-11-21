package com.example.app.service;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.model.Enums.MedicalReportType;
import com.example.app.model.MedicalReport;
import com.example.app.model.Resident;
import com.example.app.repositories.MedicalReportRepo;
import com.example.app.repositories.MoveRepo;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicalReportService {

    public static void fill(GridPane pane, ChoiceBox<String> box){
        box.setItems(FXCollections.observableArrayList(MedicalReportType.getTypes()));
        pane.getChildren().clear();
        pane.setVgap(20);

        pane.add(new Label("Здоров"), 0, 0);
        pane.add(new Label("Годен до"), 1, 0);
        pane.add(new Label("Доктор"), 2, 0);
        pane.add(new Label("Тип"), 3, 0);
        pane.add(new Label("Изменить"), 4, 0);
        pane.add(new Label("Удалить"), 5, 0);
        pane.add(new Label("Восстановить"), 6, 0);

        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }
        }

        for (int i = 0; i < AppState.getInstance().getResident().getMedicalReports().size(); i++) {
            MedicalReport report = AppState.getInstance().getResident().getMedicalReports().get(i);
            int rowIndex = i + 1;

            CheckBox healthCheckBox = new CheckBox();
            healthCheckBox.setSelected(report.isFit());
            pane.add(healthCheckBox, 0, rowIndex);

            DatePicker validUntilPicker = new DatePicker(report.getValidUntil());
            pane.add(validUntilPicker, 1, rowIndex);

            TextField doctorTextField = new TextField(report.getDoctor());
            pane.add(doctorTextField, 2, rowIndex);

            ChoiceBox<String> typeChoiceBox = new ChoiceBox<>();
            typeChoiceBox.setItems(FXCollections.observableArrayList(MedicalReportType.getTypes()));
            typeChoiceBox.setValue(report.getType().getType());
            pane.add(typeChoiceBox, 3, rowIndex);


            Button editButton = new Button("Изменить");
            editButton.setOnAction(e -> {
                try {
                    update(report);
                    updateResident();
                    updatePage();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            editButton.setDisable(true);
            pane.add(editButton, 4, rowIndex);

            Button deleteButton = new Button("Удалить");
            deleteButton.setOnAction(e -> {
                try {
                    delete(report);
                    updateResident();
                    updatePage();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            pane.add(deleteButton, 5, rowIndex);

            Button restoreButton = new Button("Восстановить");
            restoreButton.setOnAction(e -> {
                healthCheckBox.setSelected(report.isFit());
                validUntilPicker.setValue(report.getValidUntil());
                doctorTextField.setText(report.getDoctor());
                typeChoiceBox.setValue(report.getType().getType());
                editButton.setDisable(true);
                deleteButton.setDisable(false);
            });
            pane.add(restoreButton, 6, rowIndex);

            healthCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> checkEditAndDeleteButtonState(healthCheckBox, validUntilPicker, doctorTextField, typeChoiceBox, report, editButton, deleteButton));
            validUntilPicker.valueProperty().addListener((obs, oldVal, newVal) -> checkEditAndDeleteButtonState(healthCheckBox, validUntilPicker, doctorTextField, typeChoiceBox, report, editButton, deleteButton));
            doctorTextField.textProperty().addListener((obs, oldVal, newVal) -> checkEditAndDeleteButtonState(healthCheckBox, validUntilPicker, doctorTextField, typeChoiceBox, report, editButton, deleteButton));
            typeChoiceBox.valueProperty().addListener((obs, oldVal, newVal) -> checkEditAndDeleteButtonState(healthCheckBox, validUntilPicker, doctorTextField, typeChoiceBox, report, editButton, deleteButton));

        }
    }


    public static void delete(MedicalReport report) throws Exception {
        MedicalReportRepo.delete(report.getId());
    }

    public static void save(MedicalReport report) throws Exception {
        MedicalReportRepo.add(report);
        updateResident();
        updatePage();
    }

    public static void update(MedicalReport report) throws Exception {
        MedicalReportRepo.update(report, report.getId());
    }

    private static void updateResident() throws Exception {
        Resident resident = MoveRepo.getResident(AppState.getInstance().getResidentShort().getFio());
        AppState.getInstance().setResident(resident);
    }

    private static void checkEditAndDeleteButtonState(CheckBox healthCheckBox, DatePicker validUntilPicker, TextField doctorTextField,
                                               ChoiceBox<String> typeChoiceBox, MedicalReport report, Button editButton, Button deleteButton) {
        if (hasChanges(healthCheckBox, validUntilPicker, doctorTextField, typeChoiceBox, report)) {
            editButton.setDisable(false);
        } else {
            editButton.setDisable(true);
        }
        deleteButton.setDisable(isFieldsUnchanged(healthCheckBox, validUntilPicker, doctorTextField, typeChoiceBox, report));
    }

    private static boolean hasChanges(CheckBox healthCheckBox, DatePicker validUntilPicker, TextField doctorTextField,
                               ChoiceBox<String> typeChoiceBox, MedicalReport report) {
        return !(healthCheckBox.isSelected() == report.isFit() &&
                validUntilPicker.getValue().equals(report.getValidUntil()) &&
                doctorTextField.getText().equals(report.getDoctor()) &&
                typeChoiceBox.getValue().equals(report.getType().getType()));
    }
    private static boolean isFieldsUnchanged(CheckBox healthCheckBox, DatePicker validUntilPicker, TextField doctorTextField,
                                      ChoiceBox<String> typeChoiceBox, MedicalReport report) {
        return healthCheckBox.isSelected() == report.isFit() &&
                validUntilPicker.getValue().equals(report.getValidUntil()) &&
                doctorTextField.getText().equals(report.getDoctor()) &&
                typeChoiceBox.getValue().equals(report.getType().getType());
    }

    private static void updatePage() throws IOException {
        SceneManager.switchScene("medical-reports.fxml");
        Stage stage = SceneManager.getPrimaryStage();
        stage.setMaximized(false);
        stage.setMaximized(true);
    }

}
