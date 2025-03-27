package com.example.app.model.Enums;

import java.util.ArrayList;
import java.util.List;

public enum Gender {
    MALE("мужской"),
    FEMALE("женский"),
    NOT_EXIST("не существует");

    private String text;

    Gender(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getTexts() {
        List<String> labels = new ArrayList<>();
        for (Gender gender : values()) {
            labels.add(gender.getText());
        }
        labels.remove("не существует");
        return labels;
    }

    public static List<String> getFullTexts() {
        List<String> labels = new ArrayList<>();
        for (Gender gender : values()) {
            labels.add(gender.getText());
        }
        return labels;
    }

    public static Gender fromString(String type) {
        for (Gender gender : values()) {
            if (gender.getText().equalsIgnoreCase(type)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Неизвестный тип: " + type);
    }
}
