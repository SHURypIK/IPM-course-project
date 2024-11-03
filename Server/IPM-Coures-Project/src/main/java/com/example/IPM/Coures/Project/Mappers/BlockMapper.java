package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.BlockDTO;
import com.example.IPM.Coures.Project.Entities.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.Entities.BlockEntity;
import com.example.IPM.Coures.Project.repositories.AdditionalConditionRepository;
import com.example.IPM.Coures.Project.repositories.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BlockMapper implements Mapper<BlockDTO, BlockEntity>{
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    AdditionalConditionRepository additionalConditionRepository;
    @Autowired
    FloorRepository floorRepository;

    @Override
    public BlockEntity fromDTOToEntity(BlockDTO dto) {
        BlockEntity entity = modelMapper.map(dto, BlockEntity.class);
        entity.setRooms(dto.getRooms().stream().map(roomMapper::fromDTOToEntity).collect(Collectors.toList()));
        entity.setAdditionalConditions(additionalConditionRepository.findByNameIn(dto.getAdditionalConditions()));
        entity.setFloor(floorRepository.findById(dto.getFloor()).orElse(null));
        return entity;
    }

    @Override
    public BlockDTO fromEntityToDTO(BlockEntity entity) {
        BlockDTO dto = modelMapper.map(entity, BlockDTO.class);
        try{
            dto.setFloor(entity.getFloor().getId());
        } catch (Exception e){
            dto.setFloor(-1);
        }
        dto.setRooms(entity.getRooms().stream().map(roomMapper::fromEntityToDTO).collect(Collectors.toList()));
        dto.setAdditionalConditions(entity.getAdditionalConditions().stream().map(AdditionalConditionEntity::getName).collect(Collectors.toList()));
        return dto;
    }

    @Bean
    @Override
    public BlockMapper getMapper() {
        return new BlockMapper();
    }
}
