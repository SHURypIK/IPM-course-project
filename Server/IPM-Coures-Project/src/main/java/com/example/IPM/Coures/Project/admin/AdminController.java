package com.example.IPM.Coures.Project.admin;

import com.example.IPM.Coures.Project.general.BasePagingAndSortingController;
import com.example.IPM.Coures.Project.general.MyError;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController extends BasePagingAndSortingController<AdminEntity, AdminDTO, Integer> {

    private final AdminService service;

    protected AdminController(AdminService service) {
        super(service);
        this.service = service;
    }

    @PutMapping("/auth")
    public ResponseEntity authorization(@RequestBody AdminDTO dto) throws MyError {
        try{
            return ResponseEntity.ok(service.authoriz(dto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PostMapping("/new")
    public ResponseEntity create(@RequestBody AdminDTO dto) throws MyError {
        try{
            return ResponseEntity.ok(service.create(dto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PutMapping("/check")
    public ResponseEntity check(@RequestBody AdminDTO dto) throws MyError{
        try{
            return ResponseEntity.ok(service.check(dto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody AdminDTO dto) {
        try{
            return ResponseEntity.ok(service.deleteByLogin(dto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        return super.getById(id);
    }
    @GetMapping("/all")
    public ResponseEntity getAll() {
        return super.getAll();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        return super.deleteById(id);
    }
    @PutMapping("/sorting")
    public ResponseEntity getAllWithSorting(@RequestBody Sort sort) {
        return super.getAllWithSorting(sort);
    }
    @PutMapping("/paging")
    public ResponseEntity getAllWithPaging(@RequestParam int size, @RequestParam int page, @RequestBody Sort sort) {
        return super.getAllWithPaging(size, page, sort);
    }

}
