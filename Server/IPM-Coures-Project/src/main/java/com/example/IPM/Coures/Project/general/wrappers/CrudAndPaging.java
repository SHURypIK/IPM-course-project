package com.example.IPM.Coures.Project.general.wrappers;

import com.example.IPM.Coures.Project.general.Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CrudAndPaging<E extends Entity,ID> extends CrudRepository<E,ID>, PagingAndSortingRepository<E,ID> {
}
