package com.example.IPM.Coures.Project.room;

import com.example.IPM.Coures.Project.additionalCondition.AdditionalConditionDTO;
import com.example.IPM.Coures.Project.block.BlockDTO;
import com.example.IPM.Coures.Project.general.BasePagingAndSortingController;
import com.example.IPM.Coures.Project.general.Enums.Gender;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/room")
public class RoomController extends BasePagingAndSortingController<RoomEntity,RoomDTO,Integer> {

    private final RoomService service;
    protected RoomController(RoomService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody RoomDTO dto) {
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
    public ResponseEntity update(@PathVariable Integer id, @RequestBody RoomDTO dto) {
        return super.update(id, dto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        return super.deleteById(id);
    }
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody RoomDTO dto) {
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
    public ResponseEntity updateWithId(@PathVariable Integer id, @RequestBody RoomDTO dto) {
        return super.updateWithId(id,dto);
    }
    @PutMapping("/findByBlock")
    public ResponseEntity findByBlock(@RequestBody BlockDTO dto) {
        try {
            return ResponseEntity.ok(service.findByBlock(dto));
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
    @GetMapping("/findByGender")
    public ResponseEntity findByGender(@RequestParam Gender gender) {
        try {
            return ResponseEntity.ok(service.findByGender(gender));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteByBlock")
    public ResponseEntity deleteByBlock(@RequestBody BlockDTO dto) {
        try {

            return ResponseEntity.ok( service.deleteByBlock(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
