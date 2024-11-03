package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.ResponsiblePersonDTO;
import com.example.IPM.Coures.Project.Entities.PositionEntity;
import com.example.IPM.Coures.Project.Entities.ResponsiblePersonEntity;
import com.example.IPM.Coures.Project.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ResponsiblePersonMapper implements Mapper<ResponsiblePersonDTO, ResponsiblePersonEntity>{
    @Autowired
    private PositionRepository positionRepository;
    @Override
    public ResponsiblePersonEntity fromDTOToEntity(ResponsiblePersonDTO dto) {
        ResponsiblePersonEntity entity = modelMapper.map(dto, ResponsiblePersonEntity.class);
        entity.setPositions(positionRepository.findByNameIn(dto.getPositions()));
        return entity;
    }

    @Override
    public ResponsiblePersonDTO fromEntityToDTO(ResponsiblePersonEntity entity) {
        ResponsiblePersonDTO dto = modelMapper.map(entity, ResponsiblePersonDTO.class);
        try {
            dto.setPositions(entity.getPositions().stream().map(PositionEntity::getName).collect(Collectors.toList()));
        } catch (Exception e) {
            dto.setPositions(null);
        }
        return dto;
    }

}
