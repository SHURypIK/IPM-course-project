package com.example.IPM.Coures.Project.block;

import com.example.IPM.Coures.Project.room.RoomDTO;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.general.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockDTO implements DTO {

    private int id;
    private int number;
    private List<RoomDTO> rooms = new ArrayList<>();
    private Gender gender;
    private List<String> additionalConditions = new ArrayList<>();
    private int floorId;

}
