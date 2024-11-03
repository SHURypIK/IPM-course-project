package com.example.IPM.Coures.Project.Entities;

import com.example.IPM.Coures.Project.Enums.SettlementBenefit;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity implements com.example.IPM.Coures.Project.Entities.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(unique = true)
    private String login;
    @Transient
    private String password;
    @Column(name = "password")
    private String hashedPassword;
    private byte[] sold;

}
