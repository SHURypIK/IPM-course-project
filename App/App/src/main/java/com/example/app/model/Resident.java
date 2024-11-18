package com.example.app.model;

import com.example.app.model.Enums.Gender;
import com.example.app.model.Enums.SettlementBenefit;
import com.example.app.model.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resident {

    private String FIO;
    private Gender gender;
    private LocalDate birthday;
    private Status status;
    private List<MedicalReport> medicalReports;
    private LeaseContract leaseContract;
    private SettlementBenefit benefit;

}
