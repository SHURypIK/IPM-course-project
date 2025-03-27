package com.example.IPM.Coures.Project.additionalCondition;

import com.example.IPM.Coures.Project.general.Enums.AdditionalConditions;
import com.example.IPM.Coures.Project.general.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalConditionDTO implements DTO {

    private int id;
    private String name;
    private List<AdditionalConditions> places = new ArrayList<>();

}
