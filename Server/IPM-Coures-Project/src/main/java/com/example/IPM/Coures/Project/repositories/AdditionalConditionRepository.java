package com.example.IPM.Coures.Project.repositories;

import com.example.IPM.Coures.Project.Entities.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.Enums.AdditionalConditions;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdditionalConditionRepository extends PagingAndSortingRepository<AdditionalConditionEntity,Integer>, CrudRepository<AdditionalConditionEntity,Integer> {

    AdditionalConditionEntity findByName(String name);

    List<AdditionalConditionEntity> findByNameIn(List<String> names);

    List<AdditionalConditionEntity> findByPlacesContaining(AdditionalConditions places);

    @Transactional
    @Modifying
    void deleteByName(String name);

}
