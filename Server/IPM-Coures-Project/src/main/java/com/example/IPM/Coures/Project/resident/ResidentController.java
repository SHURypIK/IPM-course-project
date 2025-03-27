package com.example.IPM.Coures.Project.resident;

import com.example.IPM.Coures.Project.general.BasePagingAndSortingController;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import com.example.IPM.Coures.Project.general.Enums.SettlementBenefit;
import com.example.IPM.Coures.Project.general.Enums.Status;
import com.example.IPM.Coures.Project.room.RoomDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resident")
public class ResidentController extends BasePagingAndSortingController<ResidentEntity,ResidentDTO,String> {

    private final ResidentService service;
    protected ResidentController(ResidentService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ResidentDTO dto) {
        try {
            return ResponseEntity.ok(service.save(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        return super.getById(id);
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return super.getAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody ResidentDTO dto) {
        return super.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        return super.deleteById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody ResidentDTO dto) {
        return super.delete(dto);
    }

    @PutMapping("/sorting")
    public ResponseEntity getAllWithSorting(@RequestBody Sort sort) {
        return super.getAllWithSorting(sort);
    }
    @PutMapping("/paging")
    public ResponseEntity getAllWithPaging(@RequestParam int size, @RequestParam int page, @RequestBody Sort sort) {
        return super.getAllWithPaging(size, page, sort);
    }
    @PutMapping("/withId/{id}")
    public ResponseEntity updateWithId(@PathVariable String id, @RequestBody ResidentDTO dto) {
        return super.updateWithId(id,dto);
    }
    @GetMapping("/findByStatus")
    public ResponseEntity findByStatus(@RequestParam Status status) {
        try {
            return ResponseEntity.ok(service.findByStatus(status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/findByBenefit")
    public ResponseEntity findByBenefit(@RequestParam SettlementBenefit benefit) {
        try {
            return ResponseEntity.ok(service.findByBenefit(benefit));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/findByRoomId")
    public ResponseEntity findByRoomId(@RequestParam int roomId) {
        try {
            return ResponseEntity.ok(service.findByRoomId(roomId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/findByGender")
    public ResponseEntity findByGender(@RequestBody Gender gender) {
        try {
            return ResponseEntity.ok(service.findByGender(gender));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/findByStatusAndBenefit")
    public ResponseEntity findByStatusAndBenefit(@RequestParam Status status, @RequestParam SettlementBenefit benefit) {
        try {
            return ResponseEntity.ok(service.findByStatusAndBenefit(status,benefit));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/findByRoom")
    public ResponseEntity findByRoom(@RequestBody RoomDTO dto) {
        try {
            return ResponseEntity.ok(service.findByRoom(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/moveIn")
    public ResponseEntity moveIn(@RequestBody ResidentDTO dto,@RequestParam int id) {
        try {
            return ResponseEntity.ok(service.moveIn(dto, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/moveOut")
    public ResponseEntity moveOut(@RequestBody ResidentDTO dto,@RequestParam int id) {
        try {
            return ResponseEntity.ok(service.moveOut(dto, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/allShort")
    public ResponseEntity getAllShort() {
        try {
            return ResponseEntity.ok(service.getAllShort());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/sortingShort")
    public ResponseEntity getAllWithSortingShort(@RequestBody Sort sort) {
        try {
            return ResponseEntity.ok(service.findAllShort(sort));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/pagingShort")
    public ResponseEntity getAllWithPagingShort(@RequestParam int size, @RequestParam int page, @RequestBody Sort sort) {
        try {
            Pageable pageable = PageRequest.of(page,size,sort);
            return ResponseEntity.ok(service.findAllShort(pageable));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/find_in_pagingShort")
    public ResponseEntity findWithPagingShort(@RequestParam int size, @RequestParam int page, @RequestBody Sort sort, @RequestParam String text) {
        try {
            return ResponseEntity.ok(service.findAllShort(size, page, sort, text));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
