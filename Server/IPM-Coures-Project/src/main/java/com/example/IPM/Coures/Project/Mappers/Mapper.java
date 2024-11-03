package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.DTO;
import com.example.IPM.Coures.Project.Entities.Entity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface Mapper<DTOType extends DTO, EntityType extends Entity> {

    @Autowired
    ModelMapper modelMapper = new ModelMapper();

    public EntityType fromDTOToEntity(DTOType dto);
    public DTOType fromEntityToDTO(EntityType entity);
    public Mapper<DTOType, EntityType> getMapper();

}
