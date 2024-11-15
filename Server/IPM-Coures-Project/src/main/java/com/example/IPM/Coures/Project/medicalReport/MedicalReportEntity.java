package com.example.IPM.Coures.Project.medicalReport;

import com.example.IPM.Coures.Project.general.Enums.MedicalReportType;
import com.example.IPM.Coures.Project.resident.ResidentEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "medical_report")
@Data
public class MedicalReportEntity implements com.example.IPM.Coures.Project.general.Entity<String> {
    @Id
    @Column(name = "certificate_number")
    private String id;
    private boolean fit;
    private String doctor;
    @Column(name = "valid_until")
    private Date validUntil;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedicalReportType type;
    @ManyToOne
    @JoinColumn(name = "patient", nullable = false, foreignKey = @ForeignKey(name = "patient"))
    private ResidentEntity patient;

    @Override
    public String getId() {
        return id;
    }
}
