package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.LeaseContractDTO;
import com.example.IPM.Coures.Project.Entities.LeaseContractEntity;
import com.example.IPM.Coures.Project.Exceptions.RecordNotFoundException;
import com.example.IPM.Coures.Project.repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LeaseContractMapper implements Mapper<LeaseContractDTO, LeaseContractEntity>{

    @Autowired
    ResidentRepository residentRepository;

    @Override
    public LeaseContractEntity fromDTOToEntity(LeaseContractDTO dto) {
        LeaseContractEntity entity = modelMapper.map(dto, LeaseContractEntity.class);
        entity.setTenant(residentRepository.findById(dto.getTenant()).orElse(null));
        return entity;
    }

    @Override
    public LeaseContractDTO fromEntityToDTO(LeaseContractEntity entity) {
        LeaseContractDTO dto = modelMapper.map(entity, LeaseContractDTO.class);
        try {
            dto.setTenant(entity.getTenant().getFIO());
        } catch (Exception e){
            dto.setTenant(null);
        }
        return dto;
    }
    @Bean
    @Override
    public LeaseContractMapper getMapper() {
        return new LeaseContractMapper();
    }

}
