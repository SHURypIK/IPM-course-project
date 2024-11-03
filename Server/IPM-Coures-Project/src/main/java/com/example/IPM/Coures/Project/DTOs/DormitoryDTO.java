package com.example.IPM.Coures.Project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryDTO implements DTO{

    private int id;
    private List<FloorDTO> floors = new ArrayList<>();
    private List<String> additionalConditions = new ArrayList<>();
    private String address;
    private List<String> responsiblePersons = new ArrayList<>();

}
