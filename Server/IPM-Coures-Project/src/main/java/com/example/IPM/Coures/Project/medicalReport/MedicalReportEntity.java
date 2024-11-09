package com.example.IPM.Coures.Project.medicalReport;

import com.example.IPM.Coures.Project.resident.ResidentEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "medical_report")
@Data
public class MedicalReportEntity implements com.example.IPM.Coures.Project.general.Entity<Long> {
    @Id
    @Column(name = "certificate_number")
    private long id;
    private boolean fit;
    private String doctor;
    @Column(name = "valid_until")
    private Date validUntil;
    @ManyToOne
    @JoinColumn(name = "patient", nullable = false)
    private ResidentEntity patient;

    @Override
    public Long getId() {
        return id;
    }
}
