package com.example.IPM.Coures.Project.Entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "floor")
@Data
public class FloorEntity implements com.example.IPM.Coures.Project.Entities.Entity {

    @Id
    @Column(name = "number")
    private int id;
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private List<BlockEntity> blocks = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "add-conditional_floor", joinColumns = @JoinColumn(name = "floor"), inverseJoinColumns = @JoinColumn(name = "additional_condition"))
    private List<AdditionalConditionEntity> additionalConditions = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "responsible_person")
    private ResponsiblePersonEntity responsiblePerson;
    @ManyToOne
    @JoinColumn(name = "dormitory")
    private DormitoryEntity dormitory;
}
