package com.example.app.model;

import com.example.app.model.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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

    public Room(int id, int number, int numberOfPlaces, int numberOfAvailablePlaces, List<Resident> residents, Gender gender, List<String> additionalConditions, int blockId) {
        this.id = id;
        this.number = number;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfAvailablePlaces = numberOfAvailablePlaces;
        this.residents = residents;
        this.gender = gender;
        this.additionalConditions = additionalConditions;
        this.blockId = blockId;
    }

    public Room() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public void setNumberOfAvailablePlaces(int numberOfAvailablePlaces) {
        this.numberOfAvailablePlaces = numberOfAvailablePlaces;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAdditionalConditions(List<String> additionalConditions) {
        this.additionalConditions = additionalConditions;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
}
