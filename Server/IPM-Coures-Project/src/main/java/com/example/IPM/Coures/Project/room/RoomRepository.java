package com.example.IPM.Coures.Project.room;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.block.BlockEntity;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.general.wrappers.CrudAndPaging;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface RoomRepository extends CrudAndPaging<RoomEntity,Integer> {

    List<RoomEntity> findByGender(Gender gender);

    List<RoomEntity> findByAdditionalConditionsContaining(AdditionalConditionEntity additionalCondition);

    List<RoomEntity> findByBlock(BlockEntity block);

    @Transactional
    @Modifying
    void deleteByBlock(BlockEntity block);

}
