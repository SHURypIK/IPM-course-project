package com.example.IPM.Coures.Project.responsiblePerson;

import com.example.IPM.Coures.Project.position.PositionEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "responsible_person")
@Data
public class ResponsiblePersonEntity implements com.example.IPM.Coures.Project.general.Entity<String> {

    @Id
    @Column(name = "FIO")
    private String FIO;
    private String phone;
    private String mail;
    @ManyToMany
    @JoinTable(name = "person_position", joinColumns = @JoinColumn(name = "person", foreignKey = @ForeignKey(name = "FK_person", value = ConstraintMode.CONSTRAINT)), inverseJoinColumns = @JoinColumn(name = "position_i", foreignKey = @ForeignKey(name = "FK_position", value = ConstraintMode.CONSTRAINT)))
    private List<PositionEntity> positions = new ArrayList<>();

    @Override
    public String getId() {
        return FIO;
    }
}
