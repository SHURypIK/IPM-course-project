package com.example.IPM.Coures.Project.dormitory;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DormitoryRepository extends CrudRepository<DormitoryEntity,Integer> {

    DormitoryEntity findByAddress(String address);

    List<DormitoryEntity> findByAdditionalConditionsContaining(AdditionalConditionEntity additionalCondition);

}
