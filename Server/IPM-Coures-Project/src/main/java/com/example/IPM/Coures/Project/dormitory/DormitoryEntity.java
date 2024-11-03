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
public class DormitoryEntity implements com.example.IPM.Coures.Project.general.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number")
    private int id;
    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<FloorEntity> floors = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "add-conditional_dormitory", joinColumns = @JoinColumn(name = "dormitory"), inverseJoinColumns = @JoinColumn(name = "additional_condition"))
    private List<AdditionalConditionEntity> additionalConditions = new ArrayList<>();
    @Column(unique = true)
    private String address;
    @ManyToMany
    @JoinTable(name = "dormitory-responsible_persons", joinColumns = @JoinColumn(name = "dormitory"), inverseJoinColumns = @JoinColumn(name = "responsible_persons"))
    private List<ResponsiblePersonEntity> responsiblePersons = new ArrayList<>();
}
