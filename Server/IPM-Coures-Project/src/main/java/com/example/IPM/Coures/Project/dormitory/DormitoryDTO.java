package com.example.IPM.Coures.Project.dormitory;

import com.example.IPM.Coures.Project.floor.FloorDTO;
import com.example.IPM.Coures.Project.general.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryDTO implements DTO {

    private int id;
    private int number;
    private List<FloorDTO> floors = new ArrayList<>();
    private List<String> additionalConditions = new ArrayList<>();
    private String address;
    private List<String> responsiblePersons = new ArrayList<>();

}
