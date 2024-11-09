package com.example.IPM.Coures.Project.general;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePagingAndSortingService<T extends Entity, D extends DTO, ID> extends BaseCrudService<T,D,ID>{

    protected final PagingAndSortingRepository<T, ID> pagingAndSortingRepository;
    public BasePagingAndSortingService(CrudRepository<T, ID> repository, Mapper<D, T> mapper, PagingAndSortingRepository<T, ID> pagingAndSortingRepository) {
        super(repository, mapper);
        this.pagingAndSortingRepository = pagingAndSortingRepository;
    }

    public List<D> findAll(Sort sort) throws MyError {
        List<D> ds= null;
        try {
            ds = new ArrayList<>();
            for(T t :pagingAndSortingRepository.findAll(sort)){
                ds.add(mapper.fromEntityToDTO(t));
            }
        }catch (Exception e) {
            throw new MyError("Не получилось найти все записи с сортировкой");
        }
        return ds;
    }

    public Page<D> findAll(Pageable pageable) throws MyError {
        try {
            Page<T> page = pagingAndSortingRepository.findAll(pageable);
            return page.map(mapper::fromEntityToDTO);
        } catch (Exception e) {
            throw new MyError("Не получилось найти записи с пагинацией");
        }
    }
}
