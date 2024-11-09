package com.example.IPM.Coures.Project.leaseContract;

import com.example.IPM.Coures.Project.resident.ResidentEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "lease_contract")
@Data
public class LeaseContractEntity implements com.example.IPM.Coures.Project.general.Entity<Integer> {
    @Id
    @Column(name = "lease_contract_number")
    private int id;
    private String lessee;
    @Column(name = "valid_until")
    private Date validUntil;
    @Column(name = "contract_date")
    private Date contractDate;
    @OneToOne
    @JoinColumn(name = "tenant", nullable = false)
    private ResidentEntity tenant;

    @Override
    public Integer getId() {
        return id;
    }
}
