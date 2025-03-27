package com.example.app.model.Enums;

import java.util.ArrayList;
import java.util.List;

public enum SettlementBenefit {
    NOT_IMPORTANT("После всех"),
    USUAL("Обычно"),
    IMPORTANT("Приоритетно"),
    VERY_IMPORTANT("Вне очереди");

    private String text;

    SettlementBenefit(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getTexts() {
        List<String> labels = new ArrayList<>();
        for (SettlementBenefit benefit : values()) {
            labels.add(benefit.getText());
        }
        return labels;
    }

    public static SettlementBenefit fromString(String type) {
        for (SettlementBenefit benefit : values()) {
            if (benefit.getText().equalsIgnoreCase(type)) {
                return benefit;
            }
        }
        throw new IllegalArgumentException("Неизвестный тип: " + type);
    }

}
