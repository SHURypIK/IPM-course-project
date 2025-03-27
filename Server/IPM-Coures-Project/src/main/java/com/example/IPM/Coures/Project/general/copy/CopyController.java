package com.example.IPM.Coures.Project.general.copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/db_copy")
public class CopyController {

    @Autowired
    private CopyService backupService;

    @GetMapping("/backup")
    public ResponseEntity triggerBackup() {
        return ResponseEntity.ok(backupService.createBackup());
    }

}
