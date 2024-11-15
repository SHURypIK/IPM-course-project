package com.example.IPM.Coures.Project.additionalCondition;

import com.example.IPM.Coures.Project.general.BasePagingAndSortingService;
import com.example.IPM.Coures.Project.general.Enums.AdditionalConditions;
import com.example.IPM.Coures.Project.general.MyError;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalConditionService extends BasePagingAndSortingService<AdditionalConditionEntity,AdditionalConditionDTO,Integer> {

    private final AdditionalConditionRepository repository;
    private final AdditionalConditionMapper mapper;


    public AdditionalConditionService(AdditionalConditionRepository repository, AdditionalConditionMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public AdditionalConditionDTO findByName(String name) throws MyError {
        try {
            return mapper.fromEntityToDTO(repository.findByName(name));
        } catch (Exception e) {
            throw new MyError("не получилось найти по имени");
        }
    }

    public List<AdditionalConditionDTO> findByNameIn(List<String> names) throws MyError {
        try {
            return repository.findByNameIn(names).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти по именам");
        }
    }

    public List<AdditionalConditionDTO> findByPlacesContaining(AdditionalConditions places) throws MyError {
        try {
            return repository.findByPlacesContaining(places).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти по месту");
        }
    }
}
