package com.example.IPM.Coures.Project.floor;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.dormitory.DormitoryEntity;
import com.example.IPM.Coures.Project.responsiblePerson.ResponsiblePersonEntity;
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
