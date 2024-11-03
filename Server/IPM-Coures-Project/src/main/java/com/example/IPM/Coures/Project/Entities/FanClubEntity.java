package com.example.IPM.Coures.Project.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FanClubEntity {

    @Id
    @Column(name = "person")
    private String person;
    @Column(nullable = false)
    private String reason;
}
