package com.example.IPM.Coures.Project.room;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.block.BlockEntity;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoomRepository extends PagingAndSortingRepository<RoomEntity,Integer>, CrudRepository<RoomEntity,Integer> {

    List<RoomEntity> findByGender(Gender gender);

    List<RoomEntity> findByAdditionalConditionsContaining(AdditionalConditionEntity additionalCondition);

    List<RoomEntity> findByBlock(BlockEntity block);

    @Transactional
    @Modifying
    void deleteByBlock(BlockEntity block);

}
