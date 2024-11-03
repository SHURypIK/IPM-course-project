package com.example.IPM.Coures.Project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsiblePersonDTO implements DTO{

    private String FIO;
    private String phone;
    private String mail;
    private List<String> positions = new ArrayList<>();

}
