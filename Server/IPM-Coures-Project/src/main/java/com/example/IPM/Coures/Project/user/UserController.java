package com.example.IPM.Coures.Project.user;

import com.example.IPM.Coures.Project.general.BasePagingAndSortingController;
import com.example.IPM.Coures.Project.general.MyError;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BasePagingAndSortingController<UserEntity,UserDTO, Integer> {

    private final UserService service;
    protected UserController(UserService service) {
        super(service);
        this.service = service;
    }
    @PutMapping("/auth")
    public ResponseEntity authorization(@RequestBody UserDTO dto) throws MyError {
        try{
            return ResponseEntity.ok(service.authoriz(dto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(false);
        }
    }
    @PostMapping("/new")
    public ResponseEntity create(@RequestBody UserDTO dto) throws MyError {
        try {
            return ResponseEntity.ok(service.create(dto));
        } catch (Exception e) {
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
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody UserDTO dto) {
        try{
            return ResponseEntity.ok(service.deleteByLogin(dto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(false);
        }
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
