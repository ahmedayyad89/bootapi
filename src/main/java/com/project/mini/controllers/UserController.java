package com.project.mini.controllers;

import com.project.mini.model.User;
import com.project.mini.services.UserService;
import com.project.mini.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping(value= "/user/login", method= GET)
    public User login()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails ud = (UserDetails)(auth).getPrincipal();
        User newUser = userService.findByemail(ud.getUsername());
        newUser.setId(null);
        newUser.setPassword(null);
        return newUser;
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
//    public User get(@PathVariable Integer id)
//    {
//        return userService.findById(id);
//    }
//
//    @RequestMapping(value= "/user/email/{email}", method = {GET})
//    public User get(@PathVariable String email)
//    {
//        try {
//            User newUser = userService.findByemail(email);
//            return newUser;
//        }
//        catch(Exception e) {
//            return null;
//        }
//    }

    @RequestMapping(value = "/save/user", method = {POST,PUT})
    public User save(@Validated(UserValidator.class)  @RequestBody User user, BindingResult result)
    {
        userValidator.validate(user, result);
        if(result.hasErrors())
        {
            throw new ValidationException("There was a validation error.");
        }
        if(!userService.exists(user.getEmail()))
        {
            try {
                user.setId(null);
                User newUser =  userService.save(user);
                newUser.setId(null);
                newUser.setPassword(null);
                return newUser;
            }
            catch (Exception e)
            {
                return user;
            }
        }
        else
        {
            return user;
        }
    }

}
