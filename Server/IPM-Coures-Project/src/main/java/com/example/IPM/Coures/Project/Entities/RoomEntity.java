package com.example.IPM.Coures.Project.Entities;

import com.example.IPM.Coures.Project.Enums.Gender;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
@Data
public class RoomEntity implements com.example.IPM.Coures.Project.Entities.Entity {

    @Id
    @Column(name = "number")
    private int id;
    @Column(name = "number_of_places")
    private int numberOfPlaces;
    @Column(name = "number_of_available_places")
    private int numberOfAvailablePlaces;
    @OneToMany(mappedBy = "room")
    private List<ResidentEntity> residents = new ArrayList<>();
    @Column(name = "designed_for")
    private Gender gender;
    @ManyToMany
    @JoinTable(name = "add-conditional_room", joinColumns = @JoinColumn(name = "room"), inverseJoinColumns = @JoinColumn(name = "additional_condition"))
    private List<AdditionalConditionEntity> additionalConditions = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "block")
    private BlockEntity block;
}
