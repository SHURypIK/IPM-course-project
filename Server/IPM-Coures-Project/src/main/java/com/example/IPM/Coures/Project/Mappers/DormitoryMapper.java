package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.DormitoryDTO;
import com.example.IPM.Coures.Project.Entities.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.Entities.DormitoryEntity;
import com.example.IPM.Coures.Project.Entities.ResponsiblePersonEntity;
import com.example.IPM.Coures.Project.repositories.AdditionalConditionRepository;
import com.example.IPM.Coures.Project.repositories.ResponsiblePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DormitoryMapper implements Mapper<DormitoryDTO, DormitoryEntity>{

    @Autowired
    FloorMapper floorMapper;
    @Autowired
    AdditionalConditionRepository additionalConditionRepository;
    @Autowired
    ResponsiblePersonRepository responsiblePersonRepository;

    @Override
    public DormitoryEntity fromDTOToEntity(DormitoryDTO dto) {
        DormitoryEntity entity = modelMapper.map(dto, DormitoryEntity.class);
        entity.setAdditionalConditions(additionalConditionRepository.findByNameIn(dto.getAdditionalConditions()));
        entity.setFloors(dto.getFloors().stream().map(floorMapper::fromDTOToEntity).collect(Collectors.toList()));
        entity.setResponsiblePersons(responsiblePersonRepository.findByIdIn(dto.getResponsiblePersons()));
        return entity;
    }

    @Override
    public DormitoryDTO fromEntityToDTO(DormitoryEntity entity) {
        DormitoryDTO dto = modelMapper.map(entity, DormitoryDTO.class);
        dto.setFloors(entity.getFloors().stream().map(floorMapper::fromEntityToDTO).collect(Collectors.toList()));
        dto.setAdditionalConditions(entity.getAdditionalConditions().stream().map(AdditionalConditionEntity::getName).collect(Collectors.toList()));
        dto.setResponsiblePersons(entity.getResponsiblePersons().stream().map(ResponsiblePersonEntity::getFIO).collect(Collectors.toList()));
        return dto;

    }

    @Bean
    @Override
    public DormitoryMapper getMapper() {
        return new DormitoryMapper();
    }
}
