package com.example.IPM.Coures.Project.floor;

import com.example.IPM.Coures.Project.block.BlockDTO;
import com.example.IPM.Coures.Project.general.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorDTO implements DTO {

    private int id;
    private int number;
    private List<BlockDTO> blocks = new ArrayList<>();
    private List<String> additionalConditions = new ArrayList<>();
    private String responsiblePerson;
    private int dormitoryId;

}
