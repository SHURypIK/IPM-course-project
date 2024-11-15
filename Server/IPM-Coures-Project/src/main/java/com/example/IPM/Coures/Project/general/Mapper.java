package com.example.IPM.Coures.Project.general;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface Mapper<DTOType extends DTO, EntityType extends Entity> {

    public EntityType fromDTOToEntity(DTOType dto);
    public DTOType fromEntityToDTO(EntityType entity);


}
