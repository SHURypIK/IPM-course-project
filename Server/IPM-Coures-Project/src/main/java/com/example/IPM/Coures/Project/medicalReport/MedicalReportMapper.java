package com.example.IPM.Coures.Project.medicalReport;

import com.example.IPM.Coures.Project.general.Mapper;
import com.example.IPM.Coures.Project.resident.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicalReportMapper implements Mapper<MedicalReportDTO, MedicalReportEntity> {
    @Autowired
    private ResidentRepository residentRepository;
    @Override
    public MedicalReportEntity fromDTOToEntity(MedicalReportDTO dto) {
        MedicalReportEntity entity = modelMapper.map(dto,MedicalReportEntity.class);
        entity.setPatient(residentRepository.findById(dto.getPatient()).orElse(null));
        return entity;
    }

    @Override
    public MedicalReportDTO fromEntityToDTO(MedicalReportEntity entity) {
        MedicalReportDTO dto = modelMapper.map(entity,MedicalReportDTO.class);
        try{
            dto.setPatient(entity.getPatient().getFIO());
        } catch(Exception e){
            dto.setPatient(null);
        }
        return dto;
    }

}
