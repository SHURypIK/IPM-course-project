package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.FloorDTO;
import com.example.IPM.Coures.Project.Entities.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.Entities.FloorEntity;
import com.example.IPM.Coures.Project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FloorMapper implements Mapper<FloorDTO, FloorEntity>{

    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private AdditionalConditionRepository additionalConditionRepository;
    @Autowired
    private DormitoryRepository dormitoryRepository;
    @Autowired
    private ResponsiblePersonRepository responsiblePersonRepository;

    @Override
    public FloorEntity fromDTOToEntity(FloorDTO dto) {
        FloorEntity entity = modelMapper.map(dto, FloorEntity.class);
        entity.setResponsiblePerson(responsiblePersonRepository.findById(dto.getResponsiblePerson()).orElse(null));
        entity.setAdditionalConditions(additionalConditionRepository.findByNameIn(dto.getAdditionalConditions()));
        entity.setDormitory(dormitoryRepository.findById(dto.getDormitory()).orElse(null));
        try {
            entity.setBlocks(dto.getBlocks().stream().map(blockMapper::fromDTOToEntity).collect(Collectors.toList()));
        } catch (Exception e) {
            entity.setBlocks(null);
        }
        return entity;
    }

    @Override
    public FloorDTO fromEntityToDTO(FloorEntity entity) {
        FloorDTO dto = modelMapper.map(entity, FloorDTO.class);
        try{
            dto.setDormitory(entity.getDormitory().getId());
        } catch(Exception e){
            dto.setDormitory(-1);
        }
        try {
            dto.setBlocks(entity.getBlocks().stream().map(blockMapper::fromEntityToDTO).collect(Collectors.toList()));
        } catch (Exception e) {
            dto.setBlocks(null);
        }
        try {
            dto.setAdditionalConditions(entity.getAdditionalConditions().stream().map(AdditionalConditionEntity::getName).collect(Collectors.toList()));
        } catch (Exception e) {
            dto.setAdditionalConditions(null);
        }
        try {
            dto.setResponsiblePerson(entity.getResponsiblePerson().getFIO());
        } catch (Exception e) {
           dto.setResponsiblePerson(null);
        }
        return dto;
    }

}
