package com.finastra.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="shubham_emp")
public class Employee {
    private String name;  
    @Id
    private String userId;
    @Column(nullable = false,unique = true)
    private String employeeId;  
    @Column(nullable = false,unique = true)
    private String emailId;
    @Column(nullable = false,unique = true)
    private Long phone;
    private String password;
    private Boolean delFlg;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
