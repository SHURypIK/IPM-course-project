package com.example.IPM.Coures.Project.responsiblePerson;

import com.example.IPM.Coures.Project.position.PositionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResponsiblePersonRepository extends CrudRepository<ResponsiblePersonEntity, String> {
    List<ResponsiblePersonEntity> findByPositionsContaining(PositionEntity position);

    List<ResponsiblePersonEntity> findByIdIn(List<String> ids);
}
