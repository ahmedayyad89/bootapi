package com.project.mini.service;

import com.project.mini.exception.UserAlreadyExistsException;
import com.project.mini.dro.UserDRO;
import com.project.mini.dto.UserDTO;
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

    public UserDTO findById(Integer id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()) {
            throw new UsernameNotFoundException
                    ("UserModel With id="+id+" has not been found.");
        }
        return userOptional.get().toDTO();
    }
    public UserDTO save(UserDRO userDRO) throws Exception{
        if (exists(userDRO.getEmail())){
            throw new UserAlreadyExistsException("User Email Already exists");
        }
        UserModel userModel = new UserModel(null ,
                userDRO.getName() , userDRO.getEmail() , userDRO.getPassword() ,
                userDRO.getMobileNumber() , "ROLE_USER");
        userModel = userRepository.save(userModel);
        UserDTO userDTO = new UserDTO(userModel.getName() , userModel.getEmail() ,
                userModel.getMobileNumber() , userModel.getRole());
        return userDTO;

    }
    public UserDTO findByemail(String email) throws UsernameNotFoundException{
        Optional<UserModel> userOptional = userRepository.findByEmail(email);
        if(!userOptional.isPresent()) {
            throw new UsernameNotFoundException
                    ("UserModel With email="+email+" has not been found.");
        }
        return userOptional.get().toDTO();
    }
    public Boolean exists(String email) {
        return userRepository.existsByEmail(email);
    }
}
