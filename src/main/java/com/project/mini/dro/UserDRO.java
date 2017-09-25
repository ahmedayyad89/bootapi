package com.project.mini.dro;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class UserDRO {
    @NotBlank(message = "Name must not be empty!")
    private String name;

    @NotBlank(message = "Email must not be empty!")
    @Email(message = "Email does not match email format")
    private String email;

    @NotBlank(message = "Passowrd must not be empty!")
    @Size(min = 6 , max = 45 , message = "Check password size")
    private String password;

    @NotBlank(message = "Mobile Number must not be empty!")
    @Pattern(regexp = "01\\d{9}" ,
            message = "Mobile Number does not match number format")
    private String mobileNumber;
}
