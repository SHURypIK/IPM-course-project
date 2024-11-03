package com.example.IPM.Coures.Project.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class AdminEntity extends UserEntity {
    @Column(name = "access_key",nullable = false)
    private String accessKey;
}
