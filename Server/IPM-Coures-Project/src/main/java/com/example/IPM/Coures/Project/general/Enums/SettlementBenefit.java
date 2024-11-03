package com.example.IPM.Coures.Project.general.Enums;

public enum SettlementBenefit {
    NOT_IMPORTANT("LightRed"),
    USUAL("White"),
    IMPORTANT("LightGreen"),
    VERY_IMPORTANT("Green");

    private String color;

    SettlementBenefit(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
