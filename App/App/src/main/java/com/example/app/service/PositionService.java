package com.example.app.service;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.model.Enums.MedicalReportType;
import com.example.app.model.MedicalReport;
import com.example.app.model.Position;
import com.example.app.repositories.PositionRepo;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class PositionService {

    public static void fill(GridPane pane){

        pane.getChildren().clear();
        pane.setVgap(20);

        pane.add(new Label("Должность"), 0, 0);
        pane.add(new Label("Изменить"), 1, 0);
        pane.add(new Label("Удалить"), 2, 0);
        pane.add(new Label("Восстановить"), 3, 0);

        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }
        }

        ArrayList<Position> positions = new ArrayList<>();
        try {
            positions = PositionRepo.get();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Предупреждение закроется автоматически");

            alert.show();

            PauseTransition pause = new PauseTransition(Duration.seconds(7));
            pause.setOnFinished(event -> alert.close());
            pause.play();
        }

        for (int i = 0; i < positions.size(); i++) {
            Position position = positions.get(i);
            int rowIndex = i + 1;

            TextField positionField = new TextField(position.getName());
            pane.add(positionField, 0, rowIndex);

            Button editButton = new Button("Изменить");
            editButton.setOnAction(e -> {
                try {
                    Position positionForUpdate = new Position();
                    positionForUpdate.setName(positionField.getText());
                    positionForUpdate.setId(position.getId());
                    update(positionForUpdate);
                    updatePage();
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(ex.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");

                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(event -> alert.close());
                    pause.play();
                }
            });
            editButton.setDisable(true);
            pane.add(editButton, 1, rowIndex);

            Button deleteButton = new Button("Удалить");
            deleteButton.setOnAction(e -> {
                try {
                    delete(position);
                    updatePage();
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(ex.getMessage());
                    alert.setContentText("Предупреждение закроется автоматически");

                    alert.show();

                    PauseTransition pause = new PauseTransition(Duration.seconds(7));
                    pause.setOnFinished(event -> alert.close());
                    pause.play();
                }
            });
            pane.add(deleteButton, 2, rowIndex);

            Button restoreButton = new Button("Восстановить");
            restoreButton.setOnAction(e -> {
                positionField.setText(position.getName());
                editButton.setDisable(true);
                deleteButton.setDisable(false);
            });
            pane.add(restoreButton, 3, rowIndex);

            positionField.textProperty().addListener((obs, oldVal, newVal) -> checkEditAndDeleteButtonState(positionField,position.getName(), editButton, deleteButton));

        }
    }

    public static void delete(Position position) throws Exception {
        PositionRepo.delete(position.getId());
    }

    public static void save(Position position) throws Exception {
        PositionRepo.add(position);
        updatePage();
    }

    public static void update(Position position) throws Exception {
        PositionRepo.update(position, position.getId());
    }

    private static void updatePage() throws IOException {
        SceneManager.switchScene("position.fxml");
        Stage stage = SceneManager.getPrimaryStage();
        stage.setMaximized(false);
        stage.setMaximized(true);
    }


    private static void checkEditAndDeleteButtonState(TextField textField, String originalValue, Button editButton, Button deleteButton) {
        if (hasChanges(textField, originalValue)) {
            editButton.setDisable(false);
            deleteButton.setDisable(true);
        } else {
            editButton.setDisable(true);
            deleteButton.setDisable(false);
        }
    }

    private static boolean hasChanges(TextField textField, String originalValue) {
        return !textField.getText().equals(originalValue);
    }

}
