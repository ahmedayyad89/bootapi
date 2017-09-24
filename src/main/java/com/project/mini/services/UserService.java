package com.project.mini.services;

import com.project.mini.model.User;
import com.project.mini.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent())
        {
            throw new UsernameNotFoundException("User With id="+id+" has not been found.");
        }
        return userOptional.get();
    }
    public User save(User user) {
        try{
            User userSaved =  userRepository.save(user);
            return userSaved;
        }
        catch(Exception e)
        {
            return user;
        }

    }
    public User findByemail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(!userOptional.isPresent())
        {
            throw new UsernameNotFoundException("User With email="+email+" has not been found.");
        }
        return userOptional.get();
    }
    public Boolean exists(String email) {
        return userRepository.existsByEmail(email);
    }
}
