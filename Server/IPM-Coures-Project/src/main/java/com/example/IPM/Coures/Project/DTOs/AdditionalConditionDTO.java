package com.example.IPM.Coures.Project.DTOs;

import com.example.IPM.Coures.Project.Enums.AdditionalConditions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalConditionDTO implements DTO{

    private int id;
    private String name;
    private List<AdditionalConditions> place = new ArrayList<>();

}
