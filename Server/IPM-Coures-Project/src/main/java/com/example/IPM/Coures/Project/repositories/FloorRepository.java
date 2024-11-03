package com.example.IPM.Coures.Project.repositories;

import com.example.IPM.Coures.Project.Entities.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.Entities.DormitoryEntity;
import com.example.IPM.Coures.Project.Entities.FloorEntity;
import com.example.IPM.Coures.Project.Entities.ResponsiblePersonEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FloorRepository extends CrudRepository<FloorEntity,Integer> {

    List<FloorEntity> findByDormitory(DormitoryEntity dormitory);

    List<FloorEntity> findByResponsiblePerson(ResponsiblePersonEntity responsiblePerson);

    List<FloorEntity> findByAdditionalConditionsContaining(AdditionalConditionEntity additionalCondition);

    @Transactional
    @Modifying
    void deleteByDormitory(DormitoryEntity dormitory);

}
