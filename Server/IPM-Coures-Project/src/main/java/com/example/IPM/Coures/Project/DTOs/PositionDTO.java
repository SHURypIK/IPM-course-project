package com.example.IPM.Coures.Project.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO implements DTO{

    private int id;
    private String name;

}
