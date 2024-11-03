package com.example.IPM.Coures.Project.DTOs;

import com.example.IPM.Coures.Project.Enums.Gender;
import com.example.IPM.Coures.Project.Enums.SettlementBenefit;
import com.example.IPM.Coures.Project.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentShortDTO implements ShortDTO{

    private String FIO;
    private Gender gender;
    private Status status;
    private SettlementBenefit benefit;
}
