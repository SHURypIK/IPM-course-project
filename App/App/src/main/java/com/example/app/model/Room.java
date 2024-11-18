package com.example.app.model;

import com.example.app.model.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room{

    private int id;
    private int number;
    private int numberOfPlaces;
    private int numberOfAvailablePlaces;
    private List<Resident> residents = new ArrayList<>();
    private Gender gender;
    private List<String> additionalConditions = new ArrayList<>();
    private int blockId;

}
