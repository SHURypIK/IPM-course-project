package com.example.IPM.Coures.Project.DTOs;

import com.example.IPM.Coures.Project.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockDTO implements DTO{

    private int id;
    private List<RoomDTO> rooms = new ArrayList<>();
    private Gender gender;
    private List<String> additionalConditions = new ArrayList<>();
    private int floor;

}
