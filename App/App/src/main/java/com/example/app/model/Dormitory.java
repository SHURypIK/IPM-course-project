package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class Dormitory {

    private int id;
    private int number;
    private List<Floor> floors = new ArrayList<>();
    private List<String> additionalConditions = new ArrayList<>();
    private String address;
    private ArrayList<String> responsiblePersons = new ArrayList<>();

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

    public ArrayList<String> getResponsiblePersons() {
        return responsiblePersons;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public void setAdditionalConditions(List<String> additionalConditions) {
        this.additionalConditions = additionalConditions;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setResponsiblePersons(ArrayList<String> responsiblePersons) {
        this.responsiblePersons = responsiblePersons;
    }

    public Dormitory(int id, int number, List<Floor> floors, List<String> additionalConditions, String address, ArrayList<String> responsiblePersons) {
        this.id = id;
        this.number = number;
        this.floors = floors;
        this.additionalConditions = additionalConditions;
        this.address = address;
        this.responsiblePersons = responsiblePersons;
    }

    public Dormitory() {
    }

    public static Dormitory findByNumber(List<Dormitory> dormitories, int number){
        for(Dormitory dormitory : dormitories)
            if(dormitory.getNumber() == number)
                return dormitory;
        return null;
    }
}
