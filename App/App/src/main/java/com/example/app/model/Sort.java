package com.example.app.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Sort {

    private ArrayList<String> properties;
    private String direction;

    public Sort(ArrayList<String> properties, String direction) {
        this.properties = properties;
        this.direction = direction;
    }
}
