package com.example.app.model;

import com.example.app.model.Enums.AdditionalConditions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class AdditionalCondition {

    private int id;
    private String name;
    private List<AdditionalConditions> places = new ArrayList<>();

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<AdditionalConditions> getPlaces() {
        return places;
    }
    public void setPlaces(List<AdditionalConditions> places) {
        this.places = places;
    }

    public AdditionalCondition(int id, String name, List<AdditionalConditions> places) {
        this.id = id;
        this.name = name;
        this.places = places;
    }

    public AdditionalCondition() {
    }

    public static ArrayList<AdditionalCondition> getInDormitory(ArrayList<AdditionalCondition> additionalConditions){
        ArrayList<AdditionalCondition> conditions = new ArrayList<>();
        for(AdditionalCondition additionalCondition: additionalConditions)
            if(additionalCondition.getPlaces().contains(AdditionalConditions.DORMITORY))
                conditions.add(additionalCondition);
        return conditions;
    }

    public static ArrayList<AdditionalCondition> getInFloor(ArrayList<AdditionalCondition> additionalConditions){
        ArrayList<AdditionalCondition> conditions = new ArrayList<>();
        for(AdditionalCondition additionalCondition: additionalConditions){
            if(additionalCondition.getPlaces().contains(AdditionalConditions.FLOOR))
                conditions.add(additionalCondition);
        }
        return conditions;
    }

    public static ArrayList<AdditionalCondition> getInBlock(ArrayList<AdditionalCondition> additionalConditions){
        ArrayList<AdditionalCondition> conditions = new ArrayList<>();
        for(AdditionalCondition additionalCondition: additionalConditions)
            if(additionalCondition.getPlaces().contains(AdditionalConditions.BLOCK))
                conditions.add(additionalCondition);
        return conditions;
    }

    public static ArrayList<AdditionalCondition> getInRoom(ArrayList<AdditionalCondition> additionalConditions){
        ArrayList<AdditionalCondition> conditions = new ArrayList<>();
        for(AdditionalCondition additionalCondition: additionalConditions)
            if(additionalCondition.getPlaces().contains(AdditionalConditions.ROOM))
                conditions.add(additionalCondition);
        return conditions;
    }
}
