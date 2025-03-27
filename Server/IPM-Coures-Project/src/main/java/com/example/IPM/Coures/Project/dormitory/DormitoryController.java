package com.example.IPM.Coures.Project.dormitory;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionDTO;
import com.example.IPM.Coures.Project.general.BaseCrudController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dormitory")
public class DormitoryController extends BaseCrudController<DormitoryEntity,DormitoryDTO,Integer> {

    private final DormitoryService service;
    protected DormitoryController(DormitoryService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody DormitoryDTO dto) {
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
    public ResponseEntity update(@PathVariable Integer id, @RequestBody DormitoryDTO dto) {
        return super.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody DormitoryDTO dto) {
        return super.delete(dto);
    }

    @PutMapping("/withId/{id}")
    public ResponseEntity updateWithId(@PathVariable Integer id, @RequestBody DormitoryDTO dto) {
        return super.updateWithId(id,dto);
    }


    @GetMapping("/findByAddress")
    public ResponseEntity findByAddress(@RequestParam String address) {
       try {
           return ResponseEntity.ok(service.findByAddress(address));
       } catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @PutMapping("/findByAdditionalConditionsContaining")
    public ResponseEntity findByAdditionalConditionsContaining(@RequestBody AdditionalConditionDTO dto) {
        try {
            return ResponseEntity.ok(service.findByAdditionalConditionsContaining(dto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
