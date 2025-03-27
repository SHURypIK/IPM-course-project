package com.example.IPM.Coures.Project.position;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Table(name = "position_t")
@Data
public class PositionEntity implements com.example.IPM.Coures.Project.general.Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(unique = true)
    private String name;


    @Override
    public Integer getId() {
        return id;
    }

}
