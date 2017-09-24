package com.project.mini.model;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String mobileNumber;

    @Column
    private String role;

}
