package com.example.app.model;

import com.example.app.model.Enums.MedicalReportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalReport {

    private String id;
    private boolean fit;
    private String doctor;
    private LocalDate validUntil;
    private String patientFIO;
    private MedicalReportType type;
}
