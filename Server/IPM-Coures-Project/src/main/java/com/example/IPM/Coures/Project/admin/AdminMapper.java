package com.example.IPM.Coures.Project.admin;

import com.example.IPM.Coures.Project.general.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper implements Mapper<AdminDTO, AdminEntity> {

    @Override
    public AdminEntity fromDTOToEntity(AdminDTO dto) {
        return modelMapper.map(dto,AdminEntity.class);
    }

    @Override
    public AdminDTO fromEntityToDTO(AdminEntity entity) {
        return modelMapper.map(entity, AdminDTO.class);
    }

}
