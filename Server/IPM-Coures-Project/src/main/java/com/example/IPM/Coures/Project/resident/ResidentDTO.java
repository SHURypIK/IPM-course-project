package com.example.IPM.Coures.Project.resident;

import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.general.Enums.SettlementBenefit;
import com.example.IPM.Coures.Project.general.Enums.Status;
import com.example.IPM.Coures.Project.general.DTO;
import com.example.IPM.Coures.Project.leaseContract.LeaseContractDTO;
import com.example.IPM.Coures.Project.medicalReport.MedicalReportDTO;
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

    public List<MedicalReportDTO> getMedicalReports() {
        return medicalReports;
    }

    public LeaseContractDTO getLeaseContract() {
        return leaseContract;
    }
}
