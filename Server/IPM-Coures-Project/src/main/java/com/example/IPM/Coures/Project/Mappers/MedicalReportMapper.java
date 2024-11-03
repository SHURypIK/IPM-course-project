package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.MedicalReportDTO;
import com.example.IPM.Coures.Project.Entities.MedicalReportEntity;
import com.example.IPM.Coures.Project.Exceptions.RecordNotFoundException;
import com.example.IPM.Coures.Project.repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MedicalReportMapper implements Mapper<MedicalReportDTO, MedicalReportEntity>{
    @Autowired
    ResidentRepository residentRepository;
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

    @Bean
    @Override
    public MedicalReportMapper getMapper() {
        return new MedicalReportMapper();
    }


}
