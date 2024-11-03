package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.PositionDTO;
import com.example.IPM.Coures.Project.Entities.PositionEntity;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper implements Mapper<PositionDTO, PositionEntity>{
    @Override
    public PositionEntity fromDTOToEntity(PositionDTO dto) {
        return modelMapper.map(dto, PositionEntity.class);
    }

    @Override
    public PositionDTO fromEntityToDTO(PositionEntity entity) {
        return modelMapper.map(entity, PositionDTO.class);
    }

}
