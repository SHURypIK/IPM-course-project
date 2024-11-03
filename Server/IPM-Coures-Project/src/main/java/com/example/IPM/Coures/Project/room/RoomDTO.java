package com.example.IPM.Coures.Project.room;

import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.general.DTO;
import com.example.IPM.Coures.Project.resident.ResidentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO implements DTO {

    private int id;
    private int numberOfPlaces;
    private int numberOfAvailablePlaces;
    private List<ResidentDTO> residents = new ArrayList<>();
    private Gender gender;
    private List<String> additionalConditions = new ArrayList<>();
    private int block;

}
