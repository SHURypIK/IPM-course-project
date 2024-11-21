package com.example.app.model;

import com.example.app.model.Enums.Gender;
import com.example.app.model.Enums.SettlementBenefit;
import com.example.app.model.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class Resident {

    private String fio;
    private Gender gender;
    private LocalDate birthday;
    private Status status;
    private List<MedicalReport> medicalReports;
    private LeaseContract leaseContract;
    private SettlementBenefit benefit;

    public String getFio() {
        return fio;
    }
    public Gender getGender() {
        return gender;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public Status getStatus() {
        return status;
    }
    public List<MedicalReport> getMedicalReports() {
        return medicalReports;
    }
    public LeaseContract getLeaseContract() {
        return leaseContract;
    }
    public SettlementBenefit getBenefit() {
        return benefit;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setMedicalReports(List<MedicalReport> medicalReports) {
        this.medicalReports = medicalReports;
    }

    public void setLeaseContract(LeaseContract leaseContract) {
        this.leaseContract = leaseContract;
    }

    public void setBenefit(SettlementBenefit benefit) {
        this.benefit = benefit;
    }

    public Resident(String fio, Gender gender, LocalDate birthday, Status status, List<MedicalReport> medicalReports, LeaseContract leaseContract, SettlementBenefit benefit) {
        this.fio = fio;
        this.gender = gender;
        this.birthday = birthday;
        this.status = status;
        this.medicalReports = medicalReports;
        this.leaseContract = leaseContract;
        this.benefit = benefit;
    }

    public Resident() {
    }

    public Resident(String fio, Gender gender, LocalDate birthday, Status status, SettlementBenefit benefit) {
        this.fio = fio;
        this.gender = gender;
        this.birthday = birthday;
        this.status = status;
        this.benefit = benefit;
    }
}
