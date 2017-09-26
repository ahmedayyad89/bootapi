package com.project.mini.model;


import com.project.mini.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
public class UserModel {


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

    public UserModel() {

    }

    public UserDTO toDTO() {
        return new UserDTO(name , email , mobileNumber , role);
    }

}
