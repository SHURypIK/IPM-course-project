package com.example.IPM.Coures.Project.resident;


import com.example.IPM.Coures.Project.room.RoomEntity;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.general.Enums.SettlementBenefit;
import com.example.IPM.Coures.Project.general.Enums.Status;
import com.example.IPM.Coures.Project.leaseContract.LeaseContractEntity;
import com.example.IPM.Coures.Project.medicalReport.MedicalReportEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resident")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ResidentEntity implements com.example.IPM.Coures.Project.general.Entity<String> {

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
    @JoinColumn(name = "room", foreignKey = @ForeignKey(name = "room"))
    private RoomEntity room;

    @Override
    public String getId(){
        return FIO;
    }

}
