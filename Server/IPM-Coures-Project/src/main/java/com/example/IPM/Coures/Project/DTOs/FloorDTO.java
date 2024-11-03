package com.example.IPM.Coures.Project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorDTO implements DTO{

    private int id;
    private List<BlockDTO> blocks = new ArrayList<>();
    private List<String> additionalConditions = new ArrayList<>();
    private String responsiblePerson;
    private int dormitory;

}
