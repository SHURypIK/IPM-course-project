package com.example.app.model.Enums;

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
