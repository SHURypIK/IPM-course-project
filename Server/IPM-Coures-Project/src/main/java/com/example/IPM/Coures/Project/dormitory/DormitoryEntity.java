package com.example.IPM.Coures.Project.dormitory;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.floor.FloorEntity;
import com.example.IPM.Coures.Project.responsiblePerson.ResponsiblePersonEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dormitory")
@Data
public class DormitoryEntity implements com.example.IPM.Coures.Project.general.Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "number", unique = true)
    private int number;
    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<FloorEntity> floors = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "add_conditional_dormitory", joinColumns = @JoinColumn(name = "dormitory", foreignKey = @ForeignKey(name = "dormitory_condition")), inverseJoinColumns = @JoinColumn(name = "additional_condition", foreignKey = @ForeignKey(name = "condition_dormitory")))
    private List<AdditionalConditionEntity> additionalConditions = new ArrayList<>();
    @Column(unique = true)
    private String address;
    @ManyToMany
    @JoinTable(name = "dormitory_responsible_persons", joinColumns = @JoinColumn(name = "dormitory", foreignKey = @ForeignKey(name = "dormitory_person")), inverseJoinColumns = @JoinColumn(name = "responsible_persons", foreignKey = @ForeignKey(name = "person_dormitory")))
    private List<ResponsiblePersonEntity> responsiblePersons = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }
}
