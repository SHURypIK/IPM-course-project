package com.example.IPM.Coures.Project.block;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.floor.FloorEntity;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface BlockRepository extends PagingAndSortingRepository<BlockEntity,Integer>, CrudRepository<BlockEntity,Integer> {

    List<BlockEntity> findByGender(Gender gender);

    List<BlockEntity> findByAdditionalConditionsContaining(AdditionalConditionEntity additionalConditions);

    List<BlockEntity> findByFloor(FloorEntity floor);

}
