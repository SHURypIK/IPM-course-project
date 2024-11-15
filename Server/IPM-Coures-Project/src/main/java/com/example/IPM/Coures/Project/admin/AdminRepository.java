package com.example.IPM.Coures.Project.admin;

import com.example.IPM.Coures.Project.general.wrappers.CrudAndPaging;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

public interface AdminRepository extends CrudAndPaging<AdminEntity,Integer>{

    AdminEntity findByAccessKey(String accessKey);

    AdminEntity findByLogin(String login);
    @Transactional
    @Modifying
    void deleteByLogin(String login);
}
