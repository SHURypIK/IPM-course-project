package com.example.IPM.Coures.Project.Mappers;

import com.example.IPM.Coures.Project.DTOs.UserDTO;
import com.example.IPM.Coures.Project.Entities.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapper implements Mapper<UserDTO, UserEntity>{


    @Override
    public UserEntity fromDTOToEntity(UserDTO dto) {
        return  modelMapper.map(dto,UserEntity.class);
    }

    @Override
    public UserDTO fromEntityToDTO(UserEntity entity) {
        return modelMapper.map(entity,UserDTO.class);
    }
    @Bean
    @Override
    public UserMapper getMapper() {
        return new UserMapper();
    }
}
