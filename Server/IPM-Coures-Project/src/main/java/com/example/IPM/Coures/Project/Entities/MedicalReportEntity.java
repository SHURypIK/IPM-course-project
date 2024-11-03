package com.example.IPM.Coures.Project.Entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "medical_report")
@Data
public class MedicalReportEntity implements com.example.IPM.Coures.Project.Entities.Entity {
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
}
