package com.example.IPM.Coures.Project.block;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.dormitory.DormitoryEntity;
import com.example.IPM.Coures.Project.floor.FloorEntity;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.general.wrappers.CrudAndPaging;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface BlockRepository extends CrudAndPaging<BlockEntity,Integer> {

    List<BlockEntity> findByGender(Gender gender);

    List<BlockEntity> findByAdditionalConditionsContaining(AdditionalConditionEntity additionalConditions);

    List<BlockEntity> findByFloor(FloorEntity floor);

    @Transactional
    @Modifying
    void deleteByFloor(FloorEntity floor);

}
