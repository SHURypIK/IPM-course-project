package com.example.IPM.Coures.Project.repositories;

import com.example.IPM.Coures.Project.Entities.MedicalReportEntity;
import com.example.IPM.Coures.Project.Entities.ResidentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicalReportRepository extends CrudRepository<MedicalReportEntity,Long> {

    List<MedicalReportEntity> findByPatient(ResidentEntity patient);

    @Transactional
    @Modifying
    void deleteByPatient(ResidentEntity patient);
}
