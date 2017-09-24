package com.project.mini.controller;

import com.project.mini.dro.UserDRO;
import com.project.mini.dto.UserDTO;
import com.project.mini.model.UserModel;
import com.project.mini.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;


@RestController
public class UserController {


    @Autowired
            @Qualifier("userService")
    UserService userService;

    @Secured({"ROLE_ADMIN" , "ROLE_USER"})
    @RequestMapping(value= "/user/login", method= GET)
    public UserDTO login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)(auth).getPrincipal();
        UserModel newUserModel = userService.findByemail(userDetails.getUsername());
        return new UserDTO(newUserModel.getName() , newUserModel.getEmail() ,
                            newUserModel.getMobileNumber() , newUserModel.getRole());
    }

    @Secured({"ROLE_ADMIN" , "ROLE_USER"})
    @RequestMapping(value= "/user/logout", method= GET)
    public void logout(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }


    @RequestMapping(value = "/save/user", method = {POST,PUT})
    public UserDTO save(@Valid @RequestBody UserDRO userDRO) throws Exception {
        return userService.save(userDRO);
    }

}
