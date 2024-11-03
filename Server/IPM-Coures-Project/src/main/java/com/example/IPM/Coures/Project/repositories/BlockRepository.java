package com.example.IPM.Coures.Project.repositories;

import com.example.IPM.Coures.Project.Entities.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.Entities.BlockEntity;
import com.example.IPM.Coures.Project.Entities.FloorEntity;
import com.example.IPM.Coures.Project.Enums.Gender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface BlockRepository extends PagingAndSortingRepository<BlockEntity,Integer>, CrudRepository<BlockEntity,Integer> {

    List<BlockEntity> findByGender(Gender gender);

    List<BlockEntity> findByAdditionalConditionsContaining(AdditionalConditionEntity additionalConditions);

    List<BlockEntity> findByFloor(FloorEntity floor);

}
