package com.project.mini.controller;

import com.project.mini.model.UserModel;
import com.project.mini.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping(value= "/user/login", method= GET)
    public UserModel login()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails ud = (UserDetails)(auth).getPrincipal();
        UserModel newUserModel = userService.findByemail(ud.getUsername());
        newUserModel.setId(null);
        newUserModel.setPassword(null);
        return newUserModel;
    }


    @RequestMapping(value= "/user/logout", method= GET)
    public void logout(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ;
    }



//    @RequestMapping(value= "/user/id/{id}", method = {GET})
//    public UserModel get(@PathVariable Integer id)
//    {
//        return userService.findById(id);
//    }
//
//    @RequestMapping(value= "/user/email/{email}", method = {GET})
//    public UserModel get(@PathVariable String email)
//    {
//        try {
//            UserModel newUser = userService.findByemail(email);
//            return newUser;
//        }
//        catch(Exception e) {
//            return null;
//        }
//    }

    @RequestMapping(value = "/save/user", method = {POST,PUT})
    public UserModel save(@Valid @RequestBody UserModel userModel, BindingResult result)
    {

        if(result.hasErrors())
        {
            throw new ValidationException("There was a validation error.");
        }
        if(!userService.exists(userModel.getEmail()))
        {
            try {
                userModel.setId(null);
                UserModel newUserModel =  userService.save(userModel);
                newUserModel.setId(null);
                newUserModel.setPassword(null);
                return newUserModel;
            }
            catch (Exception e)
            {
                return userModel;
            }
        }
        else
        {
            return userModel;
        }
    }

}
