package com.example.IPM.Coures.Project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalReportDTO implements DTO{

    private long id;
    private boolean fit;
    private String doctor;
    private Date validUntil;
    private String patient;
}
