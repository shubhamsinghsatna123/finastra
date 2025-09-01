package com.example.springbootmvc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="shu_user")
public  class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

}
