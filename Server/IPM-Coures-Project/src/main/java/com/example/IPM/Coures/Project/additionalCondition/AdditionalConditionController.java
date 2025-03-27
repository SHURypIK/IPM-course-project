package com.example.IPM.Coures.Project.additionalCondition;

import com.example.IPM.Coures.Project.general.BasePagingAndSortingController;
import com.example.IPM.Coures.Project.general.Enums.AdditionalConditions;
import com.example.IPM.Coures.Project.general.Enums.SettlementBenefit;
import com.example.IPM.Coures.Project.general.Enums.Status;
import com.example.IPM.Coures.Project.resident.ResidentDTO;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/additional_condition")
public class AdditionalConditionController extends BasePagingAndSortingController<AdditionalConditionEntity,AdditionalConditionDTO,Integer> {

    private final AdditionalConditionService service;
    protected AdditionalConditionController(AdditionalConditionService service) {
        super(service);
        this.service =  service;
    }


    @PostMapping("/add")
    public ResponseEntity add(@RequestBody AdditionalConditionDTO dto) {
        return super.add(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        return super.getById(id);
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return super.getAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody AdditionalConditionDTO dto) {
        return super.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        return super.deleteById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody AdditionalConditionDTO dto) {
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
    public ResponseEntity updateWithId(@PathVariable Integer id, @RequestBody AdditionalConditionDTO dto) {
        return super.updateWithId(id,dto);
    }

    @GetMapping("/findByName")
    public ResponseEntity findByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(service.findByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/findByNameIn")
    public ResponseEntity findByNameIn(@RequestBody List<String> names) {
        try {
            return ResponseEntity.ok(service.findByNameIn(names));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/findByPlacesContaining")
    public ResponseEntity findByPlacesContaining(@RequestParam AdditionalConditions place) {
        try {
            return ResponseEntity.ok(service.findByPlacesContaining(place));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
