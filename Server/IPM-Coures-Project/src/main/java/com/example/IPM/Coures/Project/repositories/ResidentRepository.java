package com.example.IPM.Coures.Project.repositories;

import com.example.IPM.Coures.Project.Entities.ResidentEntity;
import com.example.IPM.Coures.Project.Entities.RoomEntity;
import com.example.IPM.Coures.Project.Enums.Gender;
import com.example.IPM.Coures.Project.Enums.SettlementBenefit;
import com.example.IPM.Coures.Project.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;

import java.util.List;

public interface ResidentRepository extends PagingAndSortingRepository<ResidentEntity,String>, CrudRepository<ResidentEntity,String> {

    List<ResidentEntity> findByStatus(Status status);

    List<ResidentEntity> findByBenefit(SettlementBenefit benefit);

    List<ResidentEntity> findByRoom(RoomEntity room);

    List<ResidentEntity> findByGender(Gender gender);

    List<ResidentEntity> findByStatusAndBenefit(Status status, SettlementBenefit benefit);

}
