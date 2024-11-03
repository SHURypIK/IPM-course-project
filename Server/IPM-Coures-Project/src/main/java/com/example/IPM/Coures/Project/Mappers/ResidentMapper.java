package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.ResidentDTO;
import com.example.IPM.Coures.Project.DTOs.ResidentShortDTO;
import com.example.IPM.Coures.Project.Entities.ResidentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class ResidentMapper implements ShortMapper<ResidentDTO, ResidentShortDTO, ResidentEntity>{

    @Autowired
    private LeaseContractMapper leaseContractMapper;
    @Autowired
    private MedicalReportMapper medicalReportMapper;

    @Override
    public ResidentEntity fromDTOToEntity(ResidentDTO dto) {
        ResidentEntity entity = modelMapper.map(dto, ResidentEntity.class);
        try {
            entity.setLeaseContract(leaseContractMapper.fromDTOToEntity(dto.getLeaseContract()));
        } catch (Exception e) {
            entity.setLeaseContract(null);
        }
        try {
            entity.setMedicalReports(dto.getMedicalReports().stream().map(medicalReportMapper::fromDTOToEntity).collect(Collectors.toList()));
        } catch (Exception e) {
            entity.setMedicalReports(null);
        }
        return entity;
    }

    @Override
    public ResidentDTO fromEntityToDTO(ResidentEntity entity) {
       ResidentDTO dto = modelMapper.map(entity,ResidentDTO.class);
        try {
            dto.setLeaseContract(leaseContractMapper.fromEntityToDTO(entity.getLeaseContract()));
        } catch (Exception e) {
            dto.setLeaseContract(null);
        }
        try {
            dto.setMedicalReports(entity.getMedicalReports().stream().map(medicalReportMapper::fromEntityToDTO).collect(Collectors.toList()));
        } catch (Exception e) {
            dto.setMedicalReports(null);
        }
        return dto;
    }

    @Override
    public ResidentEntity fromShortDTOToEntity(ResidentShortDTO dto) {
        return modelMapper.map(dto, ResidentEntity.class);
    }

    @Override
    public ResidentShortDTO fromEntityToShortDTO(ResidentEntity entity) {
        return modelMapper.map(entity, ResidentShortDTO.class);
    }

    @Override
    public ResidentShortDTO fromDTOToShortDTO(ResidentDTO dto) {
        return modelMapper.map(dto, ResidentShortDTO.class);
    }

    @Override
    public ResidentDTO fromShortDTOToDTO(ResidentShortDTO shortDto) {
        return modelMapper.map(shortDto, ResidentDTO.class);
    }
}
