package com.example.IPM.Coures.Project.resident;

import com.example.IPM.Coures.Project.room.RoomEntity;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.general.Enums.SettlementBenefit;
import com.example.IPM.Coures.Project.general.Enums.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ResidentRepository extends PagingAndSortingRepository<ResidentEntity,String>, CrudRepository<ResidentEntity,String> {

    List<ResidentEntity> findByStatus(Status status);

    List<ResidentEntity> findByBenefit(SettlementBenefit benefit);

    List<ResidentEntity> findByRoom(RoomEntity room);

    List<ResidentEntity> findByGender(Gender gender);

    List<ResidentEntity> findByStatusAndBenefit(Status status, SettlementBenefit benefit);

}
