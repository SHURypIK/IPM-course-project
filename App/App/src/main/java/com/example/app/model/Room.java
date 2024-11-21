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

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfAvailablePlaces() {
        return numberOfAvailablePlaces;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public Gender getGender() {
        return gender;
    }

    public List<String> getAdditionalConditions() {
        return additionalConditions;
    }

    public int getBlockId() {
        return blockId;
    }
}
