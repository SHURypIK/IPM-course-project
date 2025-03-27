package com.example.IPM.Coures.Project.responsiblePerson;

import com.example.IPM.Coures.Project.position.PositionEntity;
import com.example.IPM.Coures.Project.general.Mapper;
import com.example.IPM.Coures.Project.position.PositionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ResponsiblePersonMapper implements Mapper<ResponsiblePersonDTO, ResponsiblePersonEntity> {

    private final ModelMapper modelMapper;

    @Autowired
    public ResponsiblePersonMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
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
