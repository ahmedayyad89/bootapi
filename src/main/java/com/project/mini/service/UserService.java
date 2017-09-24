package com.project.mini.service;

import com.project.mini.model.UserModel;
import com.project.mini.repository.UserRepository;
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

    public UserModel findById(Integer id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent())
        {
            throw new UsernameNotFoundException("UserModel With id="+id+" has not been found.");
        }
        return userOptional.get();
    }
    public UserModel save(UserModel userModel) {
        try{
            UserModel userModelSaved =  userRepository.save(userModel);
            return userModelSaved;
        }
        catch(Exception e)
        {
            return userModel;
        }

    }
    public UserModel findByemail(String email) {
        Optional<UserModel> userOptional = userRepository.findByEmail(email);
        if(!userOptional.isPresent())
        {
            throw new UsernameNotFoundException("UserModel With email="+email+" has not been found.");
        }
        return userOptional.get();
    }
    public Boolean exists(String email) {
        return userRepository.existsByEmail(email);
    }
}
