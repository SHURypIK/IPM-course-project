package com.example.IPM.Coures.Project.floor;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.responsiblePerson.ResponsiblePersonEntity;
import com.example.IPM.Coures.Project.block.BlockEntity;
import com.example.IPM.Coures.Project.dormitory.DormitoryEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "floor")
@Data
public class FloorEntity implements com.example.IPM.Coures.Project.general.Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "number")
    private int number;
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private List<BlockEntity> blocks = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "add_conditional_floor", joinColumns = @JoinColumn(name = "floor", foreignKey = @ForeignKey(name = "floor_condition")), inverseJoinColumns = @JoinColumn(name = "additional_condition", foreignKey = @ForeignKey(name = "condition_floor")))
    private List<AdditionalConditionEntity> additionalConditions = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "responsible_person", foreignKey = @ForeignKey(name = "person_floor"))
    private ResponsiblePersonEntity responsiblePerson;
    @ManyToOne
    @JoinColumn(name = "dormitory")
    private DormitoryEntity dormitory;

    @Override
    public Integer getId() {
        return id;
    }
}
