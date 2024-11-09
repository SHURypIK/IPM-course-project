package com.example.IPM.Coures.Project.room;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.block.BlockEntity;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.resident.ResidentEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
@Data
public class RoomEntity implements com.example.IPM.Coures.Project.general.Entity<Integer> {

    @Id
    @Column(name = "number")
    private int id;
    @Column(name = "number_of_places")
    private int numberOfPlaces;
    @Column(name = "number_of_available_places")
    private int numberOfAvailablePlaces;
    @OneToMany(mappedBy = "room")
    private List<ResidentEntity> residents = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "designed_for")
    private Gender gender;
    @ManyToMany
    @JoinTable(name = "add-conditional_room", joinColumns = @JoinColumn(name = "room"), inverseJoinColumns = @JoinColumn(name = "additional_condition"))
    private List<AdditionalConditionEntity> additionalConditions = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "block")
    private BlockEntity block;

    @Override
    public Integer getId() {
        return id;
    }
}
