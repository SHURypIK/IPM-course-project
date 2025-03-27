package com.example.IPM.Coures.Project.leaseContract;

import com.example.IPM.Coures.Project.resident.ResidentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LeaseContractRepository extends CrudRepository<LeaseContractEntity,Integer> {

    LeaseContractEntity findByTenant(ResidentEntity tenant);

    @Transactional
    @Modifying
    @Query("DELETE FROM LeaseContractEntity l WHERE l.tenant.id = :id")
    void deleteByTenantId(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("DELETE FROM LeaseContractEntity l WHERE l.id = :id")
    void deleteById(@Param("id") Integer id);

}
