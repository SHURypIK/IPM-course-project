package com.example.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicalReportsController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_button;

    @FXML
    private Button back_button;

    @FXML
    private DatePicker date_field;

    @FXML
    private TextField doctor_field;

    @FXML
    private CheckBox fir_field;

    @FXML
    private TextField id_field;

    @FXML
    private GridPane medical_report_pane;

    @FXML
    private ChoiceBox<String> type_field;

    @FXML
    void initialize() {
        
    }
}
