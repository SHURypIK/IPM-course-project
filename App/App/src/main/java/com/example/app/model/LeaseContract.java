package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class LeaseContract {

    private int id;
    private String lessee;
    private LocalDate validUntil;
    private LocalDate contractDate;
    private String tenantFIO;

    public int getId() {
        return id;
    }

    public String getLessee() {
        return lessee;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public String getTenantFIO() {
        return tenantFIO;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public void setTenantFIO(String tenantFIO) {
        this.tenantFIO = tenantFIO;
    }

    public LeaseContract() {
    }

    public LeaseContract(int id, String lessee, LocalDate validUntil, LocalDate contractDate, String tenantFIO) {
        this.id = id;
        this.lessee = lessee;
        this.validUntil = validUntil;
        this.contractDate = contractDate;
        this.tenantFIO = tenantFIO;
    }
}
