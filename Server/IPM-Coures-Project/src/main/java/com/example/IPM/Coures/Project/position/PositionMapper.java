package com.example.IPM.Coures.Project.position;

import com.example.IPM.Coures.Project.general.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper implements Mapper<PositionDTO, PositionEntity> {
    @Override
    public PositionEntity fromDTOToEntity(PositionDTO dto) {
        return modelMapper.map(dto, PositionEntity.class);
    }

    @Override
    public PositionDTO fromEntityToDTO(PositionEntity entity) {
        return modelMapper.map(entity, PositionDTO.class);
    }

}
