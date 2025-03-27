package com.example.IPM.Coures.Project.user;

import com.example.IPM.Coures.Project.general.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDTO, UserEntity> {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity fromDTOToEntity(UserDTO dto) {
        return  modelMapper.map(dto,UserEntity.class);
    }

    @Override
    public UserDTO fromEntityToDTO(UserEntity entity) {
        return modelMapper.map(entity,UserDTO.class);
    }

}
