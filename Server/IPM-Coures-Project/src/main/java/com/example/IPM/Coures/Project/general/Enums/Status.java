package com.example.IPM.Coures.Project.general.Enums;

public enum Status {
    SETTLED("заселен"),
    EXPECTING("ожидает");

    private String text;

    Status(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
