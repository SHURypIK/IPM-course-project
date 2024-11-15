package com.example.IPM.Coures.Project.dormitory;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionDTO;
import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionMapper;
import com.example.IPM.Coures.Project.general.BaseCrudService;
import com.example.IPM.Coures.Project.general.MyError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DormitoryService extends BaseCrudService<DormitoryEntity,DormitoryDTO,Integer> {

    private final DormitoryRepository repository;
    private final DormitoryMapper mapper;
    @Autowired
    private AdditionalConditionMapper additionalConditionMapper;
    public DormitoryService(DormitoryRepository repository, DormitoryMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public DormitoryDTO findByAddress(String address) throws MyError {
        try {
            return mapper.fromEntityToDTO(repository.findByAddress(address));
        } catch (Exception e) {
            throw new MyError("Не получилось найти по адресу");
        }
    }

    public List<DormitoryDTO> findByAdditionalConditionsContaining(AdditionalConditionDTO dto) throws MyError {
        try {
            return repository.findByAdditionalConditionsContaining(additionalConditionMapper.fromDTOToEntity(dto)).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("Не получилось найти по адресу");
        }
    }
}
