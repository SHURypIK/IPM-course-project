package com.example.app.model;

import com.example.app.model.Enums.Gender;
import com.example.app.model.Enums.SettlementBenefit;
import com.example.app.model.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ResidentShort {

    private String fio;
    private Gender gender;
    private Status status;
    private SettlementBenefit benefit;

    public String getFio() {
        return fio;
    }

    public Gender getGender() {
        return gender;
    }

    public Status getStatus() {
        return status;
    }

    public SettlementBenefit getBenefit() {
        return benefit;
    }

    public  ResidentShort(Resident resident){
        this.fio = resident.getFio();
        this.gender = resident.getGender();
        this.status = resident.getStatus();
        this.benefit = resident.getBenefit();
    }

    public ResidentShort() {
    }

    public ResidentShort(String fio, Gender gender, Status status, SettlementBenefit benefit) {
        this.fio = fio;
        this.gender = gender;
        this.status = status;
        this.benefit = benefit;
    }
}
