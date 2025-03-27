package com.example.IPM.Coures.Project.user;

import com.example.IPM.Coures.Project.general.wrappers.CrudAndPaging;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends CrudAndPaging<UserEntity,Integer>{
    UserEntity findByLogin(String login);
    @Transactional
    @Modifying
    void deleteByLogin(String login);
}
