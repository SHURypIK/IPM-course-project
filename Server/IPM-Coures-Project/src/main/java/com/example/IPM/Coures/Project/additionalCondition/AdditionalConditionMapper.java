package com.example.IPM.Coures.Project.additionalCondition;

import com.example.IPM.Coures.Project.general.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AdditionalConditionMapper implements Mapper<AdditionalConditionDTO, AdditionalConditionEntity> {
    @Override
    public AdditionalConditionEntity fromDTOToEntity(AdditionalConditionDTO dto) {
        return modelMapper.map(dto,AdditionalConditionEntity.class);
    }

    @Override
    public AdditionalConditionDTO fromEntityToDTO(AdditionalConditionEntity entity) {
        return modelMapper.map(entity, AdditionalConditionDTO.class);
    }

}
