package com.example.IPM.Coures.Project.leaseContract;

import com.example.IPM.Coures.Project.resident.ResidentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

public interface LeaseContractRepository extends CrudRepository<LeaseContractEntity,Integer> {

    LeaseContractEntity findByTenant(ResidentEntity tenant);

    @Transactional
    @Modifying
    void deleteByTenant(ResidentEntity tenant);

}
