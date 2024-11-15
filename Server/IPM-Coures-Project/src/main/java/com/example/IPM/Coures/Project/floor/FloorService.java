package com.example.IPM.Coures.Project.floor;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionDTO;
import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionMapper;
import com.example.IPM.Coures.Project.dormitory.DormitoryDTO;
import com.example.IPM.Coures.Project.dormitory.DormitoryMapper;
import com.example.IPM.Coures.Project.general.BaseCrudService;
import com.example.IPM.Coures.Project.general.MyError;
import com.example.IPM.Coures.Project.responsiblePerson.ResponsiblePersonDTO;
import com.example.IPM.Coures.Project.responsiblePerson.ResponsiblePersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloorService extends BaseCrudService<FloorEntity,FloorDTO,Integer> {

    private final FloorRepository repository;
    private final FloorMapper mapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private ResponsiblePersonMapper responsiblePersonMapper;
    @Autowired
    private AdditionalConditionMapper additionalConditionMapper;


    public FloorService(FloorRepository repository, FloorMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<FloorDTO> findByDormitory(DormitoryDTO dto) throws MyError {
        try {
            return repository.findByDormitory(dormitoryMapper.fromDTOToEntity(dto)).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("Не получилось найти по общежитию");
        }
    }

    public List<FloorDTO> findByAdditionalConditionsContaining(AdditionalConditionDTO dto) throws MyError {
        try {
            return repository.findByAdditionalConditionsContaining(additionalConditionMapper.fromDTOToEntity(dto)).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("Не получилось найти по общежитию");
        }
    }

    public List<FloorDTO> findByResponsiblePerson(ResponsiblePersonDTO dto) throws MyError {
        try {
            return repository.findByResponsiblePerson(responsiblePersonMapper.fromDTOToEntity(dto)).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("Не получилось найти по общежитию");
        }
    }

    public boolean deleteByDormitory(DormitoryDTO dto) throws MyError {
        try {
            repository.deleteByDormitory(dormitoryMapper.fromDTOToEntity(dto));
            return true;
        } catch (Exception e) {
            throw new MyError("Не получилось удалить по общежитию");
        }
    }
}
