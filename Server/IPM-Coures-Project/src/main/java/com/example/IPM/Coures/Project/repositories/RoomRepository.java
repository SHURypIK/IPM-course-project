package com.example.IPM.Coures.Project.repositories;

import com.example.IPM.Coures.Project.Entities.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.Entities.BlockEntity;
import com.example.IPM.Coures.Project.Entities.RoomEntity;
import com.example.IPM.Coures.Project.Enums.Gender;
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
