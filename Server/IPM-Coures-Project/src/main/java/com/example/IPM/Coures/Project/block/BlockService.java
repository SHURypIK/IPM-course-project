package com.example.IPM.Coures.Project.block;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionDTO;
import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionMapper;
import com.example.IPM.Coures.Project.floor.FloorDTO;
import com.example.IPM.Coures.Project.floor.FloorEntity;
import com.example.IPM.Coures.Project.floor.FloorMapper;
import com.example.IPM.Coures.Project.general.BasePagingAndSortingService;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.general.Enums.Status;
import com.example.IPM.Coures.Project.general.MyError;
import com.example.IPM.Coures.Project.resident.ResidentEntity;
import com.example.IPM.Coures.Project.resident.ResidentRepository;
import com.example.IPM.Coures.Project.room.RoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockService extends BasePagingAndSortingService<BlockEntity,BlockDTO,Integer> {

    private final BlockMapper mapper;
    private final BlockRepository repository;
    @Autowired
    private FloorMapper floorMapper;
    @Autowired
    private AdditionalConditionMapper additionalConditionMapper;
    @Autowired
    private ResidentRepository residentRepository;

    public BlockService(BlockRepository repository, BlockMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<BlockDTO> findByFloor(FloorDTO dto) throws MyError {
        try {
            return repository.findByFloor(floorMapper.fromDTOToEntity(dto)).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("Не получилось найти по общежитию");
        }
    }

    public List<BlockDTO> findByAdditionalConditionsContaining(AdditionalConditionDTO dto) throws MyError {
        try {
            return repository.findByAdditionalConditionsContaining(additionalConditionMapper.fromDTOToEntity(dto)).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("Не получилось найти по этажу");
        }
    }

    public boolean deleteByFloor(FloorDTO dto) throws MyError {
        try {
            repository.deleteByFloor(floorMapper.fromDTOToEntity(dto));
            return true;
        } catch (Exception e) {
            throw new MyError("Не получилось удалить по этажу");
        }
    }

    public List<BlockDTO> findByGender(Gender gender) throws MyError {
        try {
            return repository.findByGender(gender).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("Не получилось найти по общежитию");
        }
    }

    public void deleteById(int id) throws MyError {
        try {
            BlockEntity block = repository.findById(id).get();
            for(RoomEntity room : block.getRooms())
                for(ResidentEntity resident: room.getResidents()){
                    resident.setStatus(Status.EXPECTING);
                    residentRepository.save(resident);
                }
            repository.deleteById(id);
        } catch (Exception e){
            throw new MyError("Не получилось удалить" + e.getMessage());
        }
    }

}
