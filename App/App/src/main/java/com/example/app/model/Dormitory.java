package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dormitory {

    private int id;
    private int number;
    private List<Floor> floors = new ArrayList<>();
    private List<String> additionalConditions = new ArrayList<>();
    private String address;
    private List<String> responsiblePersons = new ArrayList<>();

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public List<String> getAdditionalConditions() {
        return additionalConditions;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getResponsiblePersons() {
        return responsiblePersons;
    }
}
