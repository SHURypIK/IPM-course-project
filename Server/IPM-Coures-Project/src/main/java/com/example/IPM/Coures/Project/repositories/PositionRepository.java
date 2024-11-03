package com.example.IPM.Coures.Project.repositories;

import com.example.IPM.Coures.Project.Entities.PositionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PositionRepository extends CrudRepository<PositionEntity,Integer> {

    PositionEntity findByName(String name);

    List<PositionEntity> findByNameIn(List<String> names);

    @Transactional
    @Modifying
    void deleteByName(String name);
}
