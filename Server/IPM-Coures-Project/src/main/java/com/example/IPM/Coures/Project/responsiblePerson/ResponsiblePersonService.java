package com.example.IPM.Coures.Project.responsiblePerson;

import com.example.IPM.Coures.Project.general.BaseCrudService;
import com.example.IPM.Coures.Project.general.MyError;
import com.example.IPM.Coures.Project.position.PositionDTO;
import com.example.IPM.Coures.Project.position.PositionEntity;
import com.example.IPM.Coures.Project.position.PositionMapper;
import com.example.IPM.Coures.Project.position.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponsiblePersonService extends BaseCrudService<ResponsiblePersonEntity,ResponsiblePersonDTO, String> {

    private final ResponsiblePersonRepository repository;

    private final ResponsiblePersonMapper mapper;

    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private PositionRepository positionRepository;
    public ResponsiblePersonService(ResponsiblePersonRepository repository, ResponsiblePersonMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ResponsiblePersonDTO> findByPositionsContaining(PositionDTO dto) throws MyError {
        try {
            PositionEntity positionEntity = positionMapper.fromDTOToEntity(dto);
            return repository.findByPositionsContaining(positionEntity).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти");
        }
    }

    public List<ResponsiblePersonDTO> findByFIOIn(List<String> FIOs) throws MyError {
        try {
            return repository.findByFIOIn(FIOs).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти");
        }
    }

    public List<ResponsiblePersonDTO> findByPositionsContaining(String name) throws MyError {
        try {
            return repository.findByPositionsContaining(positionRepository.findByName(name)).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти");
        }
    }
}
