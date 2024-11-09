package com.example.IPM.Coures.Project.position;

import com.example.IPM.Coures.Project.general.BaseCrudController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController extends BaseCrudController<PositionEntity, PositionDTO, Integer> {

    private final PositionService service;
    protected PositionController(PositionService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody PositionDTO dto) {
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
    public ResponseEntity update(@PathVariable Integer id, @RequestBody PositionDTO dto) {
        return super.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        return super.deleteById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody PositionDTO dto) {
        return super.delete(dto);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(service.findByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getByNames")
    public ResponseEntity getByName(@RequestBody List<String> names) {
        try {
            return ResponseEntity.ok(service.findByName(names));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
