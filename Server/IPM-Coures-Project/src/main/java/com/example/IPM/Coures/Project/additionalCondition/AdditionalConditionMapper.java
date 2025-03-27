package com.example.IPM.Coures.Project.additionalCondition;

import com.example.IPM.Coures.Project.general.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdditionalConditionMapper implements Mapper<AdditionalConditionDTO, AdditionalConditionEntity> {

    private final ModelMapper modelMapper;

    @Autowired
    public AdditionalConditionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AdditionalConditionEntity fromDTOToEntity(AdditionalConditionDTO dto) {
        return modelMapper.map(dto,AdditionalConditionEntity.class);
    }

    @Override
    public AdditionalConditionDTO fromEntityToDTO(AdditionalConditionEntity entity) {
        return modelMapper.map(entity, AdditionalConditionDTO.class);
    }

}
