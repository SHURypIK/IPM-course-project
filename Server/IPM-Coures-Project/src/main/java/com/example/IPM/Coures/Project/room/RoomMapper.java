package com.example.IPM.Coures.Project.room;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionRepository;
import com.example.IPM.Coures.Project.block.BlockRepository;
import com.example.IPM.Coures.Project.general.Mapper;
import com.example.IPM.Coures.Project.resident.ResidentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoomMapper implements Mapper<RoomDTO, RoomEntity> {
    @Autowired
    private BlockRepository blockRepository;
    @Autowired
    private AdditionalConditionRepository additionalConditionRepository;
    @Autowired
    private ResidentMapper residentMapper;
    @Override
    public RoomEntity fromDTOToEntity(RoomDTO dto) {
        RoomEntity entity = modelMapper.map(dto, RoomEntity.class);
        entity.setBlock(blockRepository.findById(dto.getBlock()).orElse(null));
        entity.setAdditionalConditions(additionalConditionRepository.findByNameIn(dto.getAdditionalConditions()));
        try {
            entity.setResidents(dto.getResidents().stream().map(residentMapper::fromDTOToEntity).collect(Collectors.toList()));
        } catch (Exception e) {
            entity.setResidents(null);
        }
        return entity;
    }

    @Override
    public RoomDTO fromEntityToDTO(RoomEntity entity) {
        RoomDTO dto = modelMapper.map(entity, RoomDTO.class);
        try{
            dto.setBlock(entity.getBlock().getId());
        } catch(Exception e){
            dto.setBlock(-1);
        }
        try {
            dto.setAdditionalConditions(entity.getAdditionalConditions().stream().map(AdditionalConditionEntity::getName).collect(Collectors.toList()));
        } catch (Exception e) {
            dto.setAdditionalConditions(null);
        }
        try {
            dto.setResidents(entity.getResidents().stream().map(residentMapper::fromEntityToDTO).collect(Collectors.toList()));
        } catch (Exception e) {
            dto.setResidents(null);
        }
        return dto;
    }

}
