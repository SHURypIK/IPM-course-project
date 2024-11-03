package com.example.IPM.Coures.Project.Entities;

import com.example.IPM.Coures.Project.Enums.AdditionalConditions;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "additional_condition")
@Data
public class AdditionalConditionEntity implements com.example.IPM.Coures.Project.Entities.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(unique = true)
    private String name;
    @ElementCollection
    @CollectionTable(name = "additional-condition_places", joinColumns = @JoinColumn(name = "additional-condition"))
    @Enumerated(EnumType.STRING)
    @Column(name = "place")
    private List<AdditionalConditions> places = new ArrayList<>();


}
