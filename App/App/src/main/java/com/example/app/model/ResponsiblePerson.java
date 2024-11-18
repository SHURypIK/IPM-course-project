package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsiblePerson {

    private String FIO;
    private String phone;
    private String mail;
    private List<String> positions = new ArrayList<>();

}
