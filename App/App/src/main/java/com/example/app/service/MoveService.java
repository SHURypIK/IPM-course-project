package com.example.app.service;

import com.example.app.AppState;
import com.example.app.SceneManager;
import com.example.app.model.*;
import com.example.app.model.Enums.Status;
import com.example.app.repositories.MoveRepo;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MoveService {

    public static void fill(GridPane user, GridPane contract, GridPane medicalReports) throws Exception {
        Resident resident = MoveRepo.getResident(AppState.getInstance().getResidentShort().getFio());
        AppState.getInstance().setResident(resident);
        resident = AppState.getInstance().getResident();
        ArrayList<Dormitory> dormitories = MoveRepo.getDormitories();
        AppState.getInstance().setDormitories(dormitories);
        ArrayList<Floor> floors = MoveRepo.getFloors();
        AppState.getInstance().setFloors(floors);
        ArrayList<Block> blocks = MoveRepo.getBlocks();
        AppState.getInstance().setBlocks(blocks);
        ArrayList<Room> rooms = MoveRepo.getRooms();
        AppState.getInstance().setRooms(rooms);
        fillResident(user, resident);
        fillContract(contract, resident.getLeaseContract());
        fillMedicalReports(medicalReports, resident.getMedicalReports());
    }
    public static void fillResident(GridPane gridPane, Resident resident){
        Label fioLabel = (Label) gridPane.lookup("#fio_label");
        if (fioLabel != null) {
            fioLabel.setText(resident.getFio());
        }
        Label genderLabel = (Label) gridPane.lookup("#gender_label");
        if (genderLabel != null) {
            genderLabel.setText(resident.getGender().getText());
        }
        Label birthdayLabel = (Label) gridPane.lookup("#birthday_label");
        if (birthdayLabel != null) {
            birthdayLabel.setText(resident.getBirthday().format(DateTimeFormatter.ofPattern("d.M.yyyy")));
        }
        Label benefitLabel = (Label) gridPane.lookup("#benefit_label");
        if (benefitLabel != null) {
           switch (resident.getBenefit()){
               case IMPORTANT -> benefitLabel.setText("Приоритетно");
               case VERY_IMPORTANT -> benefitLabel.setText("Вне очереди");
               case NOT_IMPORTANT -> benefitLabel.setText("После всех");
               case USUAL -> benefitLabel.setText("Обычно");
           }
        }
        Label statusLabel = (Label) gridPane.lookup("#status_label");
        if (statusLabel != null) {
            statusLabel.setText(resident.getStatus().getText());
        }
    }
    public static void fillContract(GridPane contractPane, LeaseContract contract) {
        Label contractDateLabel = (Label) contractPane.lookup("#contract_date_label");
        Label contractUntilDateLabel = (Label) contractPane.lookup("#contract_until_date_label");
        Label lesseeLabel = (Label) contractPane.lookup("#lessee_label");
        Label tenantLabel = (Label) contractPane.lookup("#tenat_label");

        if (contract != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            String contractDateFormatted = contract.getContractDate().format(formatter);
            String contractUntilDateFormatted = contract.getValidUntil().format(formatter);

            contractDateLabel.setText(contractDateFormatted);
            contractUntilDateLabel.setText(contractUntilDateFormatted);
            lesseeLabel.setText(contract.getLessee());
            tenantLabel.setText(contract.getTenantFIO());
        } else {
            contractDateLabel.setText("-");
            contractUntilDateLabel.setText("-");
            lesseeLabel.setText("-");
            tenantLabel.setText("-");
        }
    }
    public static void fillMedicalReports(GridPane gridPane, List<MedicalReport> reports) {
        gridPane.getChildren().clear();
        gridPane.setHgap(10);


        Label titleLabel = new Label("Мед. Справки");
        titleLabel.setFont(new Font(18));

        Button editButton = new Button("Редактировать");
        editButton.setFont(new Font(14));
        editButton.setOnAction(event -> {
            try {
                SceneManager.switchScene("medical-reports.fxml");
                Stage stage = SceneManager.getPrimaryStage();
                stage.setMaximized(false);
                stage.setMaximized(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Label header1 = new Label("Номер");
        header1.setFont(new Font(18));
        Label header2 = new Label("Здоров");
        header2.setFont(new Font(18));
        Label header3 = new Label("Годен");
        header3.setFont(new Font(18));
        Label header4 = new Label("Доктор");
        header4.setFont(new Font(18));
        Label header5 = new Label("Пациент");
        header5.setFont(new Font(18));
        Label header6 = new Label("Тип");
        header6.setFont(new Font(18));

        gridPane.add(titleLabel, 0, 0, 6, 1); // объединяем все столбцы для заголовка
        gridPane.add(editButton, 6, 0); // кнопка в последнем столбце первой строки

        // Добавим заголовки таблицы в следующую строку
        gridPane.add(header1, 0, 1);
        gridPane.add(header2, 1, 1);
        gridPane.add(header3, 2, 1);
        gridPane.add(header4, 3, 1);
        gridPane.add(header5, 4, 1);
        gridPane.add(header6, 5, 1);

        int rowIndex = 2;

        for (MedicalReport report : reports) {
            Label numberLabel = new Label(report.getId());
            numberLabel.setFont(new Font(16));
            Label healthStatusLabel;

            if(report.isFit())
                healthStatusLabel = new Label("Здоров");
            else
                healthStatusLabel = new Label("Болен");
            healthStatusLabel.setFont(new Font(16));
            Label fitStatusLabel = new Label(report.getValidUntil().format(DateTimeFormatter.ofPattern("d.M.yyyy")));
            fitStatusLabel.setFont(new Font(16));
            Label doctorLabel = new Label(report.getDoctor());
            doctorLabel.setFont(new Font(16));
            Label patientLabel = new Label(report.getPatientFIO());
            patientLabel.setFont(new Font(16));
            Label reportTypeLabel = new Label(report.getType().getType());
            reportTypeLabel.setFont(new Font(16));

            gridPane.add(numberLabel, 0, rowIndex);
            gridPane.add(healthStatusLabel, 1, rowIndex);
            gridPane.add(fitStatusLabel, 2, rowIndex);
            gridPane.add(doctorLabel, 3, rowIndex);
            gridPane.add(patientLabel, 4, rowIndex);
            gridPane.add(reportTypeLabel, 5, rowIndex);

            rowIndex++;
        }
    }

    public static void move(Room room) throws Exception {
        Resident resident;
        if(AppState.getInstance().getResident().getStatus() == Status.SETTLED) {
            resident = MoveRepo.move(room.getId(), "Out");
            room.getResidents().remove(AppState.getInstance().getResident());
        }else{
            resident = MoveRepo.move(room.getId(),"In");
            room.getResidents().add(resident);
        }
        AppState.getInstance().setResident(resident);
        AppState.getInstance().setResidentShort(new ResidentShort(resident));
        SceneManager.switchScene("move-resident.fxml");
        Stage stage = SceneManager.getPrimaryStage();
        stage.setMaximized(false);
        stage.setMaximized(true);
    }
}
