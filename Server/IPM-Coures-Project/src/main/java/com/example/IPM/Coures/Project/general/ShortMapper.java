package com.example.IPM.Coures.Project.general;

public interface ShortMapper<DTOType extends DTO, ShortDTOType extends ShortDTO, EntityType extends Entity> extends Mapper<DTOType, EntityType> {

    public EntityType fromShortDTOToEntity(ShortDTOType dto);
    public ShortDTOType fromEntityToShortDTO(EntityType entity);
    public ShortDTOType fromDTOToShortDTO(DTOType dto);
    public DTOType fromShortDTOToDTO(ShortDTOType shortDto);
}
