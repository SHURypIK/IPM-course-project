package com.example.IPM.Coures.Project.leaseContract;

import com.example.IPM.Coures.Project.general.BaseCrudController;
import com.example.IPM.Coures.Project.resident.ResidentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lease_contract")
public class LeaseContractController extends BaseCrudController<LeaseContractEntity,LeaseContractDTO,Integer> {

    private final LeaseContractService service;
    protected LeaseContractController(LeaseContractService service) {
        super(service);
        this.service = service;
    }


    @PostMapping("/add")
    public ResponseEntity add(@RequestBody LeaseContractDTO dto) {
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
    public ResponseEntity update(@PathVariable Integer id, @RequestBody LeaseContractDTO dto) {
        return super.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        return super.deleteById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody LeaseContractDTO dto) {
        try {
            service.delete(dto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/withId/{id}")
    public ResponseEntity updateWithId(@PathVariable Integer id, @RequestBody LeaseContractDTO dto) {
        return super.updateWithId(id,dto);
    }

    @PutMapping("/findByTenant")
    public ResponseEntity findByTenant(@RequestBody ResidentDTO dto) {
        try {
            return ResponseEntity.ok(service.findByTenant(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteByTenant")
    public ResponseEntity deleteByTenantId(@RequestBody ResidentDTO dto) {
        try {
            service.deleteByTenant(dto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
