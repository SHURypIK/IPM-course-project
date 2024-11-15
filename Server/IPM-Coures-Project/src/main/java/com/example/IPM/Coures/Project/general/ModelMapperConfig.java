package com.example.IPM.Coures.Project.general;

import com.example.IPM.Coures.Project.resident.ResidentDTO;
import com.example.IPM.Coures.Project.resident.ResidentEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//не нужный
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }
}
