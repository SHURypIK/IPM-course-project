package com.example.IPM.Coures.Project.block;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionEntity;
import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionRepository;
import com.example.IPM.Coures.Project.room.RoomMapper;
import com.example.IPM.Coures.Project.general.Mapper;
import com.example.IPM.Coures.Project.floor.FloorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BlockMapper implements Mapper<BlockDTO, BlockEntity> {

    private final ModelMapper modelMapper;

    @Autowired
    public BlockMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private AdditionalConditionRepository additionalConditionRepository;
    @Autowired
    private FloorRepository floorRepository;

    @Override
    public BlockEntity fromDTOToEntity(BlockDTO dto) {
        BlockEntity entity = modelMapper.map(dto, BlockEntity.class);
        try {
            entity.setRooms(dto.getRooms().stream().map(roomMapper::fromDTOToEntity).collect(Collectors.toList()));
        } catch (Exception e) {
            entity.setRooms(null);
        }
        entity.setAdditionalConditions(additionalConditionRepository.findByNameIn(dto.getAdditionalConditions()));
        entity.setFloor(floorRepository.findById(dto.getFloorId()).orElse(null));
        return entity;
    }

    @Override
    public BlockDTO fromEntityToDTO(BlockEntity entity) {
        BlockDTO dto = modelMapper.map(entity, BlockDTO.class);
        try{
            dto.setFloorId(entity.getFloor().getId());
        } catch (Exception e){
            dto.setFloorId(-1);
        }
        try {
            dto.setRooms(entity.getRooms().stream().map(roomMapper::fromEntityToDTO).collect(Collectors.toList()));
        } catch (Exception e) {
            dto.setRooms(null);
        }
        try {
            dto.setAdditionalConditions(entity.getAdditionalConditions().stream().map(AdditionalConditionEntity::getName).collect(Collectors.toList()));
        } catch (Exception e) {
            dto.setAdditionalConditions(null);
        }
        return dto;
    }

}
