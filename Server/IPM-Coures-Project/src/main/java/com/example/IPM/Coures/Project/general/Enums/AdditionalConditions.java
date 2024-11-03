package com.example.IPM.Coures.Project.general.Enums;

public enum AdditionalConditions {

    ROOM("комната"),
    BLOCK("блок"),
    FLOOR("этаж"),
    DORMITORY("общежитие");

    private String place;

    AdditionalConditions(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }
}
