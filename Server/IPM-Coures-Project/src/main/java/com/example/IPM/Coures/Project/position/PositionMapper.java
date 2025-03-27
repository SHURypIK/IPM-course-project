package com.example.IPM.Coures.Project.position;

import com.example.IPM.Coures.Project.general.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper implements Mapper<PositionDTO, PositionEntity> {

    private final ModelMapper modelMapper;

    @Autowired
    public PositionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public PositionEntity fromDTOToEntity(PositionDTO dto) {
        return modelMapper.map(dto, PositionEntity.class);
    }

    @Override
    public PositionDTO fromEntityToDTO(PositionEntity entity) {
        return modelMapper.map(entity, PositionDTO.class);
    }

}
