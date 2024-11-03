package com.example.IPM.Coures.Project.Entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "responsible_person")
@Data
public class ResponsiblePersonEntity implements com.example.IPM.Coures.Project.Entities.Entity {

    @Id
    @Column(name = "FIO")
    private String FIO;
    private String phone;
    private String mail;
    @ManyToMany
    @JoinTable(name = "person_position", joinColumns = @JoinColumn(name = "person"), inverseJoinColumns = @JoinColumn(name = "position"))
    private List<PositionEntity> positions = new ArrayList<>();

}
