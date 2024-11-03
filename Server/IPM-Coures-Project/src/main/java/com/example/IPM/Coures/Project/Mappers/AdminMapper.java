package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.AdminDTO;
import com.example.IPM.Coures.Project.Entities.AdminEntity;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper implements Mapper<AdminDTO, AdminEntity>{

    @Override
    public AdminEntity fromDTOToEntity(AdminDTO dto) {
        return modelMapper.map(dto,AdminEntity.class);
    }

    @Override
    public AdminDTO fromEntityToDTO(AdminEntity entity) {
        return modelMapper.map(entity, AdminDTO.class);
    }

}
