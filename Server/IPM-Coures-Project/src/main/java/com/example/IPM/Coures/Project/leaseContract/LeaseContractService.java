package com.example.IPM.Coures.Project.leaseContract;

import com.example.IPM.Coures.Project.general.BaseCrudService;
import com.example.IPM.Coures.Project.general.MyError;
import com.example.IPM.Coures.Project.resident.ResidentDTO;
import com.example.IPM.Coures.Project.resident.ResidentEntity;
import com.example.IPM.Coures.Project.resident.ResidentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LeaseContractService extends BaseCrudService<LeaseContractEntity,LeaseContractDTO,Integer> {

    private final LeaseContractRepository repository;
    private final LeaseContractMapper mapper;
    @Autowired
    private ResidentMapper residentMapper;


    public LeaseContractService(LeaseContractRepository repository, LeaseContractMapper mapper) {
        super(repository, mapper);
        this.mapper = mapper;
        this.repository = repository;
    }

    LeaseContractDTO findByTenant(ResidentDTO dto) throws MyError {
        try {
            ResidentEntity entity = residentMapper.fromDTOToEntity(dto);
            return mapper.fromEntityToDTO(repository.findByTenant(entity));
        } catch (Exception e) {
            throw new MyError("не получилось найти");
        }
    }

    void deleteByTenant(ResidentDTO dto) throws MyError {
        try {
            repository.deleteByTenantId(dto.getFIO());
        } catch (Exception e) {
            throw new MyError("не получилось найти" + e.getMessage());
        }
    }

    public void delete(LeaseContractDTO dto) throws MyError {
        try {
            LeaseContractEntity entity = mapper.fromDTOToEntity(dto);
            repository.deleteById(entity.getId());
        } catch (Exception e) {
            throw new MyError("не получилось найти" + e.getMessage());
        }
    }
}
