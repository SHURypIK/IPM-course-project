package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Floor {

    private int id;
    private int number;
    private List<Block> blocks = new ArrayList<>();
    private List<String> additionalConditions = new ArrayList<>();
    private String responsiblePerson;
    private int dormitoryId;

}
