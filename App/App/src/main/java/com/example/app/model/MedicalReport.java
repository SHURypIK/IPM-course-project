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

    public String getId() {
        return id;
    }

    public boolean isFit() {
        return fit;
    }

    public String getDoctor() {
        return doctor;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public String getPatientFIO() {
        return patientFIO;
    }

    public MedicalReportType getType() {
        return type;
    }
}
