package com.example.IPM.Coures.Project.repositories;

import com.example.IPM.Coures.Project.Entities.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.Entities.DormitoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DormitoryRepository extends CrudRepository<DormitoryEntity,Integer> {

    DormitoryEntity findByAddress(String address);

    List<DormitoryEntity> findByAdditionalConditionsContaining(AdditionalConditionEntity additionalCondition);

}
