package com.example.IPM.Coures.Project.user;

import com.example.IPM.Coures.Project.general.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDTO, UserEntity> {


    @Override
    public UserEntity fromDTOToEntity(UserDTO dto) {
        return  modelMapper.map(dto,UserEntity.class);
    }

    @Override
    public UserDTO fromEntityToDTO(UserEntity entity) {
        return modelMapper.map(entity,UserDTO.class);
    }

}
