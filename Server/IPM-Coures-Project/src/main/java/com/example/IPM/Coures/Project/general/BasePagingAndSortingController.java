package com.example.IPM.Coures.Project.general;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class BasePagingAndSortingController<T extends Entity, D extends DTO, ID> extends BaseCrudController<T,D,ID>{

    protected BasePagingAndSortingService<T,D,ID> service;
    protected BasePagingAndSortingController(BasePagingAndSortingService<T, D, ID> service) {
        super(service);
        this.service = service;
    }

    @PutMapping("/sorting")
    public ResponseEntity getAllWithSorting(@RequestBody Sort sort) {
        try {
            return ResponseEntity.ok(service.findAll(sort));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/paging")
    public ResponseEntity getAllWithPaging(@RequestParam int size, @RequestParam int page, @RequestBody Sort sort) {
        try {
            Pageable pageable = PageRequest.of(page,size,sort);
            return ResponseEntity.ok(service.findAll(pageable));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
