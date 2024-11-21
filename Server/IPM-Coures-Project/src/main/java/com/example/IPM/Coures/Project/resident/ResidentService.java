package com.example.IPM.Coures.Project.resident;

import com.example.IPM.Coures.Project.general.BasePagingAndSortingService;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.general.Enums.SettlementBenefit;
import com.example.IPM.Coures.Project.general.Enums.Status;
import com.example.IPM.Coures.Project.general.MyError;
import com.example.IPM.Coures.Project.leaseContract.LeaseContractEntity;
import com.example.IPM.Coures.Project.room.RoomDTO;
import com.example.IPM.Coures.Project.room.RoomEntity;
import com.example.IPM.Coures.Project.room.RoomMapper;
import com.example.IPM.Coures.Project.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResidentService extends BasePagingAndSortingService<ResidentEntity, ResidentDTO, String> {

    private final ResidentRepository repository;

    private final ResidentMapper mapper;

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomRepository roomRepository;
    public ResidentService(ResidentRepository repository, ResidentMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ResidentDTO> findByStatus(Status status) throws MyError {
        try {
            return repository.findByStatus(status).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти по статусу");
        }
    }

    public List<ResidentDTO> findByBenefit(SettlementBenefit benefit) throws MyError {
        try {
            return repository.findByBenefit(benefit).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти по льготе");
        }
    }

    public List<ResidentDTO> findByRoom(RoomDTO dto) throws MyError {
        try {
            RoomEntity entity = roomMapper.fromDTOToEntity(dto);
            return repository.findByRoom(entity).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти по комноте");
        }
    }

    public List<ResidentDTO> findByRoomId(int id) throws MyError {
        try {
            return repository.findByRoomId(id).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти по id комнаты");
        }
    }

    public List<ResidentDTO> findByGender(Gender gender) throws MyError {
        try {
            return repository.findByGender(gender).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти по гендеру");
        }
    }

    public List<ResidentDTO> findByStatusAndBenefit(Status status, SettlementBenefit benefit) throws MyError {
        try {
            return repository.findByStatusAndBenefit(status, benefit).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти по статусу с льготой");
        }
    }

    public ResidentDTO moveIn(ResidentDTO dto, int roomId) throws MyError {
        try {
            ResidentEntity resident = mapper.fromDTOToEntity(dto);
            resident.setStatus(Status.SETTLED);
            RoomEntity room = roomRepository.findById(roomId).get();
            resident.setRoom(room);
            ResidentEntity entity = repository.save(resident);
            room.setNumberOfAvailablePlaces(room.getNumberOfAvailablePlaces()-1);
            List<ResidentEntity> residentEntities = room.getResidents();
            residentEntities.add(entity);
            room.setResidents(residentEntities);
            roomRepository.save(room);
            return mapper.fromEntityToDTO(resident);
        } catch (Exception e) {
            throw new MyError("не получилось заселить" + e.getMessage());
        }
    }

    public ResidentDTO moveOut(ResidentDTO dto, int roomId) throws MyError {
        try {
            ResidentEntity resident = mapper.fromDTOToEntity(dto);
            resident.setStatus(Status.EXPECTING);
            RoomEntity room = roomRepository.findById(roomId).get();
            resident.setRoom(null);
            ResidentEntity entity = repository.save(resident);
            room.setNumberOfAvailablePlaces(room.getNumberOfAvailablePlaces()+1);
            List<ResidentEntity> residentEntities = room.getResidents();
            residentEntities.remove(resident);
            room.setResidents(residentEntities);
            roomRepository.save(room);
            return mapper.fromEntityToDTO(resident);
        } catch (Exception e) {
            throw new MyError("не получилось заселить" + e.getMessage());
        }
    }

    public ResidentDTO save(ResidentDTO dto)throws MyError {
        try {
            ResidentEntity entity = mapper.fromDTOToEntity(dto);
            if(repository.existsById(entity.getId()))
                throw new MyError("Объект уже существует");
            LeaseContractEntity leaseContract = entity.getLeaseContract();
            leaseContract.setTenant(entity);
            repository.save(entity);
            return mapper.fromEntityToDTO(entity);
        } catch (Exception e){
            throw new MyError("Не получилось сохранить" + e.getMessage());
        }
    }

    public List<ResidentShortDTO> getAllShort() throws MyError {
        try {
            List<ResidentShortDTO> residents = new ArrayList<>();
            for(ResidentEntity entity: repository.findAll()){
                residents.add(mapper.fromEntityToShortDTO(entity));
            }
            return residents;
        } catch (Exception e) {
            throw new MyError("не получилось найти по статусу с льготой");
        }
    }

    public List<ResidentShortDTO> findAllShort(Sort sort) throws MyError {
        List<ResidentShortDTO> ds= null;
        try {
            ds = new ArrayList<>();
            for(ResidentEntity t :pagingAndSortingRepository.findAll(sort)){
                ds.add(mapper.fromEntityToShortDTO(t));
            }
        }catch (Exception e) {
            throw new MyError("Не получилось найти все записи с сортировкой");
        }
        return ds;
    }

    public Page<ResidentShortDTO> findAllShort(Pageable pageable) throws MyError {
        try {
            Page<ResidentEntity> page = pagingAndSortingRepository.findAll(pageable);
            return page.map(mapper::fromEntityToShortDTO);
        } catch (Exception e) {
            throw new MyError("Не получилось найти записи с пагинацией");
        }
    }

}
