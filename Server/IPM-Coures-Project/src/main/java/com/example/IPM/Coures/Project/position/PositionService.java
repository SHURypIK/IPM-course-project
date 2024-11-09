package com.example.IPM.Coures.Project.position;

import com.example.IPM.Coures.Project.general.BaseCrudService;
import com.example.IPM.Coures.Project.general.MyError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionService extends BaseCrudService<PositionEntity, PositionDTO, Integer>{


    private final PositionRepository repository;

    private final PositionMapper mapper;
    @Autowired
    public PositionService(PositionRepository repository, PositionMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public PositionDTO findByName(String name) throws MyError {
        try {
            return mapper.fromEntityToDTO(repository.findByName(name));
        } catch (Exception e) {
            throw new MyError("не получилось найти");
        }
    }

    public List<PositionDTO> findByName(List<String> names) throws MyError {
        try {
            return repository.findByNameIn(names).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти");
        }
    }


}