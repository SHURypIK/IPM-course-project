package com.example.IPM.Coures.Project.responsiblePerson;

import com.example.IPM.Coures.Project.general.BaseCrudController;
import com.example.IPM.Coures.Project.position.PositionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsible_person")
public class ResponsiblePersonController extends BaseCrudController<ResponsiblePersonEntity,ResponsiblePersonDTO,String> {

    private final ResponsiblePersonService service;

    protected ResponsiblePersonController(ResponsiblePersonService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ResponsiblePersonDTO dto) {
        return super.add(dto);
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
    public ResponseEntity update(@PathVariable String id, @RequestBody ResponsiblePersonDTO dto) {
        return super.update(id, dto);
    }
    @PutMapping("/withId/{id}")
    public ResponseEntity updateWithId(@PathVariable String id, @RequestBody ResponsiblePersonDTO dto) {
        return super.updateWithId(id,dto);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        return super.deleteById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody ResponsiblePersonDTO dto) {
        return super.delete(dto);
    }

    @PutMapping("/getByPositionsContainingUsingDTO")
    public ResponseEntity getByPositionsContaining(@RequestBody PositionDTO dto) {
        try {
            return ResponseEntity.ok(service.findByPositionsContaining(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getByPositionsContainingUsingName")
    public ResponseEntity getByPositionsContaining(@RequestParam String name) {
        try {
            return ResponseEntity.ok(service.findByPositionsContaining(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/getByFIOIn")
    public ResponseEntity getByFIOIn(@RequestBody List<String> FIOs) {
        try {
            return ResponseEntity.ok(service.findByFIOIn(FIOs));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
