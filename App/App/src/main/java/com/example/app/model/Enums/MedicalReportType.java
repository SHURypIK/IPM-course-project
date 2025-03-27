package com.example.app.model.Enums;

import java.util.ArrayList;
import java.util.List;

public enum MedicalReportType {


    INFECTIOUS_DISEASES("об отсутсвии инфекционных заболеваниях"),
    FLUOROGRAPHY("флюрография"),
    DERMATOLOGIST("осмотр дерматолога");

    private String type;

    MedicalReportType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static List<String> getTypes() {
        List<String> labels = new ArrayList<>();
        for (MedicalReportType type : values()) {
            labels.add(type.getType());
        }
        return labels;
    }

    public static MedicalReportType fromString(String type) {
        for (MedicalReportType reportType : values()) {
            if (reportType.getType().equalsIgnoreCase(type)) {
                return reportType;
            }
        }
        throw new IllegalArgumentException("Неизвестный тип: " + type);
    }
}
