package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseContract {

    private int id;
    private String lessee;
    private LocalDate validUntil;
    private LocalDate contractDate;
    private String tenantFIO;

}
