package com.example.IPM.Coures.Project.medicalReport;

import com.example.IPM.Coures.Project.resident.ResidentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalReportRepository extends CrudRepository<MedicalReportEntity,String> {

    List<MedicalReportEntity> findByPatient(ResidentEntity patient);

    @Transactional
    @Modifying
    void deleteByPatient(ResidentEntity patient);

}
