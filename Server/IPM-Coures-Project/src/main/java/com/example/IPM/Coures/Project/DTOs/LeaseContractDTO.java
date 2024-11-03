package com.example.IPM.Coures.Project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseContractDTO implements DTO{

    private int id;
    private String lessee;
    private Date validUntil;
    private Date contractDate;
    private String tenant;

}
