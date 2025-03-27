package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private int id;
    private int number;
    private List<Block> blocks = new ArrayList<>();
    private List<String> additionalConditions = new ArrayList<>();
    private String responsiblePerson;
    private int dormitoryId;

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<String> getAdditionalConditions() {
        return additionalConditions;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public int getDormitoryId() {
        return dormitoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public void setAdditionalConditions(List<String> additionalConditions) {
        this.additionalConditions = additionalConditions;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public void setDormitoryId(int dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Floor() {
    }

    public Floor(int id, int number, List<Block> blocks, List<String> additionalConditions, String responsiblePerson, int dormitoryId) {
        this.id = id;
        this.number = number;
        this.blocks = blocks;
        this.additionalConditions = additionalConditions;
        this.responsiblePerson = responsiblePerson;
        this.dormitoryId = dormitoryId;
    }

    public static Floor findByNumber(Dormitory dormitory, int number){
        for(Floor floor: dormitory.getFloors())
            if(floor.getNumber() == number)
                return floor;
        return null;
    }
}
