package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.AdditionalConditionDTO;
import com.example.IPM.Coures.Project.Entities.AdditionalConditionEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AdditionalConditionMapper implements Mapper<AdditionalConditionDTO, AdditionalConditionEntity>{
    @Override
    public AdditionalConditionEntity fromDTOToEntity(AdditionalConditionDTO dto) {
        return modelMapper.map(dto,AdditionalConditionEntity.class);
    }

    @Override
    public AdditionalConditionDTO fromEntityToDTO(AdditionalConditionEntity entity) {
        return modelMapper.map(entity, AdditionalConditionDTO.class);
    }

    @Bean
    @Override
    public AdditionalConditionMapper getMapper() {
        return new AdditionalConditionMapper();
    }
}
