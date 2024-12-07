package com.example.app.model;

import com.example.app.model.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    public Block(int id, int number, List<Room> rooms, Gender gender, List<String> additionalConditions, int floorId) {
        this.id = id;
        this.number = number;
        this.rooms = rooms;
        this.gender = gender;
        this.additionalConditions = additionalConditions;
        this.floorId = floorId;
    }

    public Block() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAdditionalConditions(List<String> additionalConditions) {
        this.additionalConditions = additionalConditions;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public static Block findByNumber(Floor floor, int number){
        for(Block block: floor.getBlocks())
            if(block.getNumber() == number)
                return block;
        return null;
    }
}
