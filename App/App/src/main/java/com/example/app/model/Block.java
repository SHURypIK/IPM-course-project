package com.example.app.model;

import com.example.app.model.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block {

    private int id;
    private int number;
    private List<Room> rooms = new ArrayList<>();
    private Gender gender;
    private List<String> additionalConditions = new ArrayList<>();
    private int floorId;

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Gender getGender() {
        return gender;
    }

    public List<String> getAdditionalConditions() {
        return additionalConditions;
    }

    public int getFloorId() {
        return floorId;
    }
}
