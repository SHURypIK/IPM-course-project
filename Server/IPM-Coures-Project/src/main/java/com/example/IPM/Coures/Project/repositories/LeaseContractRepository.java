package com.example.IPM.Coures.Project.repositories;

import com.example.IPM.Coures.Project.Entities.LeaseContractEntity;
import com.example.IPM.Coures.Project.Entities.ResidentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

public interface LeaseContractRepository extends CrudRepository<LeaseContractEntity,Integer> {

    LeaseContractEntity findByTenant(ResidentEntity tenant);

    @Transactional
    @Modifying
    void deleteByTenant(ResidentEntity tenant);

}
