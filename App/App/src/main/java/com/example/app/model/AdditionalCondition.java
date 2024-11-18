package com.example.app.model;

import com.example.app.model.Enums.AdditionalConditions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalCondition {

    private int id;
    private String name;
    private List<AdditionalConditions> places = new ArrayList<>();

}
