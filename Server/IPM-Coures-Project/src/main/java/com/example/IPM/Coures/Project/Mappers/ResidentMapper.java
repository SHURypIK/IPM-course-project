package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.ResidentDTO;
import com.example.IPM.Coures.Project.DTOs.ResidentShortDTO;
import com.example.IPM.Coures.Project.Entities.ResidentEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class ResidentMapper implements ShortMapper<ResidentDTO, ResidentShortDTO, ResidentEntity>{

    private final LeaseContractMapper leaseContractMapper = new LeaseContractMapper();
    private final MedicalReportMapper medicalReportMapper = new MedicalReportMapper();

    @Override
    public ResidentEntity fromDTOToEntity(ResidentDTO dto) {
        ResidentEntity entity = modelMapper.map(dto, ResidentEntity.class);
        entity.setLeaseContract(leaseContractMapper.fromDTOToEntity(dto.getLeaseContract()));
        entity.setMedicalReports(dto.getMedicalReports().stream().map(medicalReportMapper::fromDTOToEntity).collect(Collectors.toList()));
        return entity;
    }

    @Override
    public ResidentDTO fromEntityToDTO(ResidentEntity entity) {
       ResidentDTO dto = modelMapper.map(entity,ResidentDTO.class);
       dto.setLeaseContract(leaseContractMapper.fromEntityToDTO(entity.getLeaseContract()));
       dto.setMedicalReports(entity.getMedicalReports().stream().map(medicalReportMapper::fromEntityToDTO).collect(Collectors.toList()));
       return dto;
    }
    @Bean
    @Override
    public ResidentMapper getMapper() {
        return new ResidentMapper();
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
