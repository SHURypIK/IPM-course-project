package com.example.IPM.Coures.Project.medicalReport;

import com.example.IPM.Coures.Project.general.Mapper;
import com.example.IPM.Coures.Project.resident.ResidentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicalReportMapper implements Mapper<MedicalReportDTO, MedicalReportEntity> {
    private final ModelMapper modelMapper;

    @Autowired
    public MedicalReportMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    private ResidentRepository residentRepository;
    @Override
    public MedicalReportEntity fromDTOToEntity(MedicalReportDTO dto) {
        MedicalReportEntity entity = modelMapper.map(dto,MedicalReportEntity.class);
        entity.setPatient(residentRepository.findById(dto.getPatientFIO()).orElse(null));
        return entity;
    }

    @Override
    public MedicalReportDTO fromEntityToDTO(MedicalReportEntity entity) {
        MedicalReportDTO dto = modelMapper.map(entity,MedicalReportDTO.class);
        try{
            dto.setPatientFIO(entity.getPatient().getFIO());
        } catch(Exception e){
            dto.setPatientFIO(null);
        }
        return dto;
    }

}
