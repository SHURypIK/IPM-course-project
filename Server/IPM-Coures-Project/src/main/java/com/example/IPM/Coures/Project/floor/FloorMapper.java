package com.example.IPM.Coures.Project.floor;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionRepository;
import com.example.IPM.Coures.Project.block.BlockMapper;
import com.example.IPM.Coures.Project.dormitory.DormitoryRepository;
import com.example.IPM.Coures.Project.general.Mapper;
import com.example.IPM.Coures.Project.responsiblePerson.ResponsiblePersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FloorMapper implements Mapper<FloorDTO, FloorEntity> {

    private final ModelMapper modelMapper;

    @Autowired
    public FloorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

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
        entity.setDormitory(dormitoryRepository.findById(dto.getDormitoryId()).orElse(null));
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
            dto.setDormitoryId(entity.getDormitory().getId());
        } catch(Exception e){
            dto.setDormitoryId(-1);
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
