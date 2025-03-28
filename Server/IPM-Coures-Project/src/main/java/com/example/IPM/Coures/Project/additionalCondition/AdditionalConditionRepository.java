package com.example.IPM.Coures.Project.additionalCondition;

import com.example.IPM.Coures.Project.general.Enums.AdditionalConditions;
import com.example.IPM.Coures.Project.general.wrappers.CrudAndPaging;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdditionalConditionRepository extends CrudAndPaging<AdditionalConditionEntity,Integer> {

    AdditionalConditionEntity findByName(String name);

    List<AdditionalConditionEntity> findByNameIn(List<String> names);

    List<AdditionalConditionEntity> findByPlacesContaining(AdditionalConditions places);

    @Transactional
    @Modifying
    void deleteByName(String name);

}
