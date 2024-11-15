package com.example.IPM.Coures.Project.general.Enums;

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
}
