package com.example.app.model;

import com.example.app.model.Enums.Gender;
import com.example.app.model.Enums.SettlementBenefit;
import com.example.app.model.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentShort {

    private String FIO;
    private Gender gender;
    private Status status;
    private SettlementBenefit benefit;
}
