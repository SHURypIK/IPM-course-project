package com.example.IPM.Coures.Project.DTOs;

import com.example.IPM.Coures.Project.Entities.LeaseContractEntity;
import com.example.IPM.Coures.Project.Entities.MedicalReportEntity;
import com.example.IPM.Coures.Project.Enums.Gender;
import com.example.IPM.Coures.Project.Enums.SettlementBenefit;
import com.example.IPM.Coures.Project.Enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDTO implements DTO {

    private String FIO;
    private Gender gender;
    private Date birthday;
    private Status status;
    private List<MedicalReportDTO> medicalReports;
    private LeaseContractDTO leaseContract;
    private SettlementBenefit benefit;
}
