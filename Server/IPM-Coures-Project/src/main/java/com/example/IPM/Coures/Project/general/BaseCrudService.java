package com.example.IPM.Coures.Project.general;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCrudService<T extends Entity,D extends DTO,ID> {

    protected final CrudRepository<T, ID> repository;
    protected final Mapper<D, T> mapper;

    public BaseCrudService(CrudRepository<T, ID> repository, Mapper<D, T> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public D save(D dto) throws MyError {
        try {
            T entity = mapper.fromDTOToEntity(dto);
            if(repository.existsById((ID) entity.getId()))
                throw new MyError("Объект уже существует");
            repository.save(entity);
            return mapper.fromEntityToDTO(entity);
        } catch (Exception e){
            throw new MyError("Не получилось сохранить" + e.getMessage());
        }
    }
    public void deleteById(ID id) throws MyError {
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new MyError("Не получилось удалить" + e.getMessage());
        }
    }
    public void delete(D dto) throws MyError {
        try {
            repository.delete(mapper.fromDTOToEntity(dto));
        } catch (Exception e){
            throw new MyError("Не получилось удалить");
        }
    }
    public D update(D dto, ID id) throws MyError {
        try {
           repository.findById(id).orElseThrow(() -> new MyError("Запись не найдена"));
           T entity = mapper.fromDTOToEntity(dto);
           if(!id.equals(entity.getId()))
               throw new MyError("Переданы разные id");
           repository.save(entity);
           return mapper.fromEntityToDTO(entity);
        } catch (Exception e){
            throw new MyError("Не получилось обновить" + e.getMessage());
        }
    }
    public D getById(ID id) throws MyError {
        return repository.findById(id).map(mapper::fromEntityToDTO).orElseThrow(() -> new MyError("Запись не найдена"));
    }
    public List<D> getAll() throws MyError {
        List<D> ds= null;
        try {
            ds = new ArrayList<>();
            for(T t :repository.findAll()){
               ds.add(mapper.fromEntityToDTO(t));
            }
        } catch (Exception e) {
            throw new MyError("Не получилось найти все записи");
        }
        return ds;
    }
}
