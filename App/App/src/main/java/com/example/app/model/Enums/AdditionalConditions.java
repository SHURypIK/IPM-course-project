package com.example.app.model.Enums;

import com.example.app.model.AdditionalCondition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static List<String> getTypes() {
        List<String> labels = new ArrayList<>();
        for (AdditionalConditions conditions : values()) {
            labels.add(conditions.getPlace());
        }
        return labels;
    }

    public static AdditionalConditions fromString(String type) {
        for (AdditionalConditions condition : values()) {
            if (condition.getPlace().equalsIgnoreCase(type)) {
                return condition;
            }
        }
        throw new IllegalArgumentException("Неизвестный тип: " + type);
    }

    public static List<AdditionalConditions> fromStrings(List<String> types) {
        Set<AdditionalConditions> places = new HashSet<>();
        for (AdditionalConditions condition : values()) {
            for (String place : types) {
                if (condition.getPlace().equalsIgnoreCase(place)) {
                    places.add(condition);
                }
            }
        }
        return new ArrayList<>(places);
    }
}
