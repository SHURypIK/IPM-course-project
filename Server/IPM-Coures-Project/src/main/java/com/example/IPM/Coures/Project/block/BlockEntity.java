package com.example.IPM.Coures.Project.block;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.floor.FloorEntity;
import com.example.IPM.Coures.Project.room.RoomEntity;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "block")
@Data
public class BlockEntity implements com.example.IPM.Coures.Project.general.Entity {

    @Id
    @Column(name = "number")
    private int id;
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL)
    private List<RoomEntity> rooms = new ArrayList<>();
    @Column(name = "designed_for")
    private Gender gender;
    @ManyToMany
    @JoinTable(name = "add-conditional_block", joinColumns = @JoinColumn(name = "block"), inverseJoinColumns = @JoinColumn(name = "additional_condition"))
    private List<AdditionalConditionEntity> additionalConditions = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "floor")
    private FloorEntity floor;


}
