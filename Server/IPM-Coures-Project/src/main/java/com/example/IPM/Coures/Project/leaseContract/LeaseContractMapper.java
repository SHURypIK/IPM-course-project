package com.example.IPM.Coures.Project.leaseContract;

import com.example.IPM.Coures.Project.general.Mapper;
import com.example.IPM.Coures.Project.resident.ResidentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeaseContractMapper implements Mapper<LeaseContractDTO, LeaseContractEntity> {

    private final ModelMapper modelMapper;

    @Autowired
    public LeaseContractMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public LeaseContractEntity fromDTOToEntity(LeaseContractDTO dto) {
        LeaseContractEntity entity = modelMapper.map(dto, LeaseContractEntity.class);
        entity.setTenant(residentRepository.findById(dto.getTenantFIO()).orElse(null));
        return entity;
    }

    @Override
    public LeaseContractDTO fromEntityToDTO(LeaseContractEntity entity) {
        LeaseContractDTO dto = modelMapper.map(entity, LeaseContractDTO.class);
        try {
            dto.setTenantFIO(entity.getTenant().getFIO());
        } catch (Exception e){
            dto.setTenantFIO(null);
        }
        return dto;
    }
}
