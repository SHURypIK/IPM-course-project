package com.example.IPM.Coures.Project.repositories;

import com.example.IPM.Coures.Project.Entities.PositionEntity;
import com.example.IPM.Coures.Project.Entities.ResponsiblePersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResponsiblePersonRepository extends CrudRepository<ResponsiblePersonEntity, String> {
    List<ResponsiblePersonEntity> findByPositionsContaining(PositionEntity position);

    List<ResponsiblePersonEntity> findByIdIn(List<String> ids);
}
