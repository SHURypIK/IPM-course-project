package com.example.IPM.Coures.Project.medicalReport;

import com.example.IPM.Coures.Project.general.BaseCrudController;
import com.example.IPM.Coures.Project.resident.ResidentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical_report")
public class MedicalReportController extends BaseCrudController<MedicalReportEntity, MedicalReportDTO,String> {

    private final MedicalReportService service;
    protected MedicalReportController(MedicalReportService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody MedicalReportDTO dto) {
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
    public ResponseEntity update(@PathVariable String id, @RequestBody MedicalReportDTO dto) {
        return super.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        return super.deleteById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody MedicalReportDTO dto) {
       return super.delete(dto);
    }

    @PutMapping("/withId/{id}")
    public ResponseEntity updateWithId(@PathVariable String id, @RequestBody MedicalReportDTO dto) {
        return super.updateWithId(id,dto);
    }

    @PutMapping("/findByPatient")
    public ResponseEntity findByPatient(@RequestBody ResidentDTO dto) {
        try {
            return ResponseEntity.ok(service.findByPatient(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteByPatient")
    public ResponseEntity deleteByPatient(@RequestBody ResidentDTO dto) {
        try {
            service.deleteByPatient(dto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
