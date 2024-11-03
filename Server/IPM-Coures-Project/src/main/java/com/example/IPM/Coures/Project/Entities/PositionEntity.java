package com.example.IPM.Coures.Project.Entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Table(name = "position")
@Data
public class PositionEntity implements com.example.IPM.Coures.Project.Entities.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(unique = true)
    private String name;

}
