package com.example.IPM.Coures.Project.medicalReport;

import com.example.IPM.Coures.Project.resident.ResidentEntity;
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
