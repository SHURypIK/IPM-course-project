package com.example.IPM.Coures.Project.dormitory;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionRepository;
import com.example.IPM.Coures.Project.floor.FloorMapper;
import com.example.IPM.Coures.Project.responsiblePerson.ResponsiblePersonEntity;
import com.example.IPM.Coures.Project.general.Mapper;
import com.example.IPM.Coures.Project.responsiblePerson.ResponsiblePersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DormitoryMapper implements Mapper<DormitoryDTO, DormitoryEntity> {

    private final ModelMapper modelMapper;

    @Autowired
    public DormitoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    private FloorMapper floorMapper;
    @Autowired
    private AdditionalConditionRepository additionalConditionRepository;
    @Autowired
    private ResponsiblePersonRepository responsiblePersonRepository;

    @Override
    public DormitoryEntity fromDTOToEntity(DormitoryDTO dto) {
        DormitoryEntity entity = modelMapper.map(dto, DormitoryEntity.class);
        entity.setAdditionalConditions(additionalConditionRepository.findByNameIn(dto.getAdditionalConditions()));
        try {
            entity.setFloors(dto.getFloors().stream().map(floorMapper::fromDTOToEntity).collect(Collectors.toList()));
        } catch (Exception e) {
            entity.setFloors(null);
        }
        entity.setResponsiblePersons(responsiblePersonRepository.findByFIOIn(dto.getResponsiblePersons()));
        return entity;
    }

    @Override
    public DormitoryDTO fromEntityToDTO(DormitoryEntity entity) {
        DormitoryDTO dto = modelMapper.map(entity, DormitoryDTO.class);
        try {
            dto.setFloors(entity.getFloors().stream().map(floorMapper::fromEntityToDTO).collect(Collectors.toList()));
        } catch (Exception e) {
            dto.setFloors(null);
        }
        try {
            dto.setAdditionalConditions(entity.getAdditionalConditions().stream().map(AdditionalConditionEntity::getName).collect(Collectors.toList()));
        } catch (Exception e) {
            dto.setAdditionalConditions(null);
        }
        try {
            dto.setResponsiblePersons(entity.getResponsiblePersons().stream().map(ResponsiblePersonEntity::getFIO).collect(Collectors.toList()));
        } catch (Exception e) {
            dto.setResponsiblePersons(null);
        }
        return dto;

    }

}
