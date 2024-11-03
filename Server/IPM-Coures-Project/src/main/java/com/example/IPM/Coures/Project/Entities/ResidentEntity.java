package com.example.IPM.Coures.Project.Entities;


import com.example.IPM.Coures.Project.Enums.Gender;
import com.example.IPM.Coures.Project.Enums.SettlementBenefit;
import com.example.IPM.Coures.Project.Enums.Status;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resident")
@Data
public class ResidentEntity implements com.example.IPM.Coures.Project.Entities.Entity {

    @Id
    @Column(name = "FIO")
    private String FIO;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    private Date birthday;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<MedicalReportEntity> medicalReports = new ArrayList<>();
    @OneToOne(mappedBy = "tenant", cascade = CascadeType.ALL)
    private LeaseContractEntity leaseContract;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Enumerated(EnumType.STRING)
    @Column(name = "benefit", nullable = false)
    private SettlementBenefit benefit;
    @ManyToOne
    @JoinColumn(name = "room")
    private RoomEntity room;

}
