package com.example.IPM.Coures.Project.general.Enums;

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
}
