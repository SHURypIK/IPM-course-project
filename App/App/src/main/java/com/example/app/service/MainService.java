package com.example.app.service;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.model.Enums.Status;
import com.example.app.model.Page;
import com.example.app.model.ResidentShort;
import com.example.app.repositories.MainRepo;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainService {

    public static void fill(GridPane gridPane, Pagination pagination) throws Exception {
        Page<ResidentShort> page = MainRepo.pagingShort(0);
        AppState.getInstance().setResidentPage(page);
        paginationSetting(page, pagination, gridPane);
        fillGridPane(gridPane, page.getContent());
    }

    public static void fillGridPane(GridPane gridPane, ArrayList<ResidentShort> residentShorts){
        gridPane.getChildren().clear();
        gridPane.setVgap(20);
        Label header1 = new Label("ФИО");
        Label header2 = new Label("Пол");
        Label header3 = new Label("Статус");
        Label header4 = new Label("Действие");
        String headerStyle = "-fx-font-weight: bold; -fx-font-size: 24px; -fx-border-color: black transparent black transparent; -fx-border-width: 1 0 1 0; -fx-alignment: CENTER_LEFT;";
        header1.setStyle(headerStyle);
        header2.setStyle(headerStyle);
        header3.setStyle(headerStyle);
        header4.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-border-color: black transparent black transparent; -fx-border-width: 1 0 1 0; -fx-alignment: CENTER_RIGHT;");
        header1.setMaxWidth(Double.MAX_VALUE);
        header2.setMaxWidth(Double.MAX_VALUE);
        header3.setMaxWidth(Double.MAX_VALUE);
        header4.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(header1, Priority.ALWAYS);
        GridPane.setHgrow(header2, Priority.ALWAYS);
        GridPane.setHgrow(header3, Priority.ALWAYS);
        GridPane.setHgrow(header4, Priority.ALWAYS);
        gridPane.add(header1, 0, 0);
        gridPane.add(header2, 1, 0);
        gridPane.add(header3, 2, 0);
        gridPane.add(header4, 3, 0);

        for (int i = 0; i < residentShorts.size(); i++) {
            ResidentShort resident = residentShorts.get(i);
            Label fioLabel = new Label(resident.getFio());
            Label genderLabel = new Label(resident.getGender().getText());
            Label statusLabel = new Label(resident.getStatus().getText());
            Color backgroundColor;
            switch (resident.getBenefit()) {
                case IMPORTANT:
                    backgroundColor = Color.LIMEGREEN;
                    break;
                case VERY_IMPORTANT:
                    backgroundColor = Color.LIGHTGREEN;
                    break;
                case NOT_IMPORTANT:
                    backgroundColor = Color.LIGHTCORAL;
                    break;
                default:
                    backgroundColor = Color.WHITE;
            }
            Background background = new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY));
            fioLabel.setBackground(background);
            genderLabel.setBackground(background);
            statusLabel.setBackground(background);
            String cellStyle = "-fx-font-size: 20px; -fx-border-color: black transparent black transparent; -fx-border-width: 1 0 1 0; -fx-alignment: CENTER_LEFT;";
            fioLabel.setStyle(cellStyle);
            genderLabel.setStyle(cellStyle);
            statusLabel.setStyle(cellStyle);
            fioLabel.setMaxWidth(Double.MAX_VALUE);
            genderLabel.setMaxWidth(Double.MAX_VALUE);
            statusLabel.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(fioLabel, Priority.ALWAYS);
            GridPane.setHgrow(genderLabel, Priority.ALWAYS);
            GridPane.setHgrow(statusLabel, Priority.ALWAYS);

            Button actionButton = new Button();
            if(resident.getStatus() == Status.SETTLED)
                actionButton.setText("Выселить");
            else
                actionButton.setText("Заселить");
            actionButton.setOnAction(event -> {
                try {
                    AppState.getInstance().setResidentShort(resident);
                    SceneManager.switchScene("move-resident.fxml");
                    Stage stage = SceneManager.getPrimaryStage();
                    stage.setMaximized(false);
                    stage.setMaximized(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            actionButton.setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(actionButton, Priority.ALWAYS);
            actionButton.setStyle("-fx-font-size: 14,5px; -fx-border-color: black transparent black transparent; -fx-border-width: 1 0 1 0; -fx-alignment: CENTER;");
            gridPane.add(fioLabel, 0, i + 1);
            gridPane.add(genderLabel, 1, i + 1);
            gridPane.add(statusLabel, 2, i + 1);
            gridPane.add(actionButton, 3, i + 1);
        }
    }

    public static void paginationSetting(Page<ResidentShort> page, Pagination pagination , GridPane gridPane){
        pagination.setCurrentPageIndex(0);
        pagination.setPageCount(page.getTotalPages());
        pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> {
            int pageIndex = newValue.intValue();
            Page<ResidentShort> newPage = null;
            try {
                newPage = MainRepo.pagingShort(pageIndex);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            AppState.getInstance().setResidentPage(newPage);
            fillGridPane(gridPane, newPage.getContent());
        });
    }
}
