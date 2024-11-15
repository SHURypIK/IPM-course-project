package com.example.IPM.Coures.Project.floor;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionDTO;
import com.example.IPM.Coures.Project.dormitory.DormitoryDTO;
import com.example.IPM.Coures.Project.general.BaseCrudController;
import com.example.IPM.Coures.Project.responsiblePerson.ResponsiblePersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/floor")
public class FloorController extends BaseCrudController<FloorEntity,FloorDTO,Integer> {

    private final FloorService service;
    protected FloorController(FloorService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody FloorDTO dto) {
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
    public ResponseEntity update(@PathVariable Integer id, @RequestBody FloorDTO dto) {
        return super.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        return super.deleteById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody FloorDTO dto) {
        return super.delete(dto);
    }

    @PutMapping("/withId/{id}")
    public ResponseEntity updateWithId(@PathVariable Integer id, @RequestBody FloorDTO dto) {
        return super.updateWithId(id,dto);
    }


    @PutMapping("/findByDormitory")
    public ResponseEntity findByDormitory(@RequestBody DormitoryDTO dto) {
        try {
            return ResponseEntity.ok(service.findByDormitory(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/findByAdditionalConditionsContaining")
    public ResponseEntity findByAdditionalConditionsContaining(@RequestBody AdditionalConditionDTO dto) {
        try {
            return ResponseEntity.ok(service.findByAdditionalConditionsContaining(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/findByResponsiblePerson")
    public ResponseEntity findByResponsiblePerson(@RequestBody ResponsiblePersonDTO dto) {
        try {
            return ResponseEntity.ok(service.findByResponsiblePerson(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteByDormitory")
    public ResponseEntity deleteByDormitory(@RequestBody DormitoryDTO dto) {
        try {

            return ResponseEntity.ok( service.deleteByDormitory(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
