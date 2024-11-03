package com.example.IPM.Coures.Project.repositories;

import com.example.IPM.Coures.Project.Entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity,Integer>, CrudRepository<UserEntity,Integer> {
    UserEntity findByLogin(String login);
    @Transactional
    @Modifying
    void deleteByLogin(String login);
}
