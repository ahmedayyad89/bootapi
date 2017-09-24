package com.project.mini.validators;

import com.project.mini.model.User;
import com.project.mini.services.UserService;


import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {



    @Autowired
    UserService userService;

    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name should not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email should not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role should not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password should not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "mobileNumber should not be empty");
        if(!user.getEmail().contains("@"))
        {
            errors.rejectValue("email", "This value should be a valid email");
        }
        if(user.getPassword().length()<6)
        {
            errors.rejectValue("password", "This value should be at least 6 characters long");
        }
        if(!user.getRole().matches("ROLE_USER") && !user.getRole().matches("ROLE_ADMIN"))
        {
            errors.rejectValue("role", "Invalid Value");
        }
        if(!user.getMobileNumber().matches("01\\d{9}"))
        {
            errors.rejectValue("mobileNumber", "should be valid Egyptian Mobile Number, 01*********");
        }


    }
}
