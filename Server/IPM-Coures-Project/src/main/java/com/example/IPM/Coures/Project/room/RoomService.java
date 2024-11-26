package com.example.IPM.Coures.Project.room;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionDTO;
import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionMapper;
import com.example.IPM.Coures.Project.block.BlockDTO;
import com.example.IPM.Coures.Project.block.BlockMapper;
import com.example.IPM.Coures.Project.general.BasePagingAndSortingService;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.general.Enums.Status;
import com.example.IPM.Coures.Project.general.MyError;
import com.example.IPM.Coures.Project.resident.ResidentEntity;
import com.example.IPM.Coures.Project.resident.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService extends BasePagingAndSortingService<RoomEntity,RoomDTO,Integer> {

    private final RoomRepository repository;
    private final RoomMapper mapper;
    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private AdditionalConditionMapper additionalConditionMapper;
    @Autowired
    private ResidentRepository residentRepository;
    public RoomService(RoomRepository repository, RoomMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<RoomDTO> findByBlock(BlockDTO dto) throws MyError {
        try {
            return repository.findByBlock(blockMapper.fromDTOToEntity(dto)).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("Не получилось найти по общежитию");
        }
    }

    public List<RoomDTO> findByAdditionalConditionsContaining(AdditionalConditionDTO dto) throws MyError {
        try {
            return repository.findByAdditionalConditionsContaining(additionalConditionMapper.fromDTOToEntity(dto)).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("Не получилось найти по этажу");
        }
    }

    public boolean deleteByBlock(BlockDTO dto) throws MyError {
        try {
            repository.deleteByBlock(blockMapper.fromDTOToEntity(dto));
            return true;
        } catch (Exception e) {
            throw new MyError("Не получилось удалить по этажу");
        }
    }

    public List<RoomDTO> findByGender(Gender gender) throws MyError {
        try {
            return repository.findByGender(gender).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("Не получилось найти по общежитию");
        }
    }


    public void deleteById(int id) throws MyError {
        try {
            RoomEntity entity = repository.findById(id).get();
            for (ResidentEntity resident: entity.getResidents()){
                resident.setStatus(Status.EXPECTING);
                residentRepository.save(resident);
            }
            repository.deleteById(id);
        } catch (Exception e){
            throw new MyError("Не получилось удалить" + e.getMessage());
        }
    }
}
