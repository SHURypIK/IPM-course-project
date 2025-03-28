package com.example.IPM.Coures.Project.admin;

import com.example.IPM.Coures.Project.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "admin")
@Data
@EqualsAndHashCode(callSuper=false)
public class AdminEntity extends UserEntity {
    @Column(name = "access_key",nullable = false)
    private String accessKey;
}
