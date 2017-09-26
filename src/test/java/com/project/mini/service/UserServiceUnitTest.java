package com.project.mini.service;

import com.project.mini.dro.UserDRO;
import com.project.mini.dto.NoteDTO;
import com.project.mini.model.NoteModel;
import com.project.mini.model.UserModel;
import com.project.mini.repository.NoteRepository;
import com.project.mini.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks  UserService userService;

    @Test
    public void findByIdValidTest() {
        UserModel userModel = new UserModel(1 , "Khaled Ahmed" ,
                "admin@orange.com" , "123456" , "01129788199" ,
                "ROLE_ADMIN");
        Optional<UserModel> optionalUserModel = Optional.of(userModel);
        when(userRepository.findById(1)).thenReturn(optionalUserModel);
        Assert.assertEquals(userService.findById(1) , userModel.toDTO());
    }

    @Test
    public void findByIdNotValidTest() {
        UserModel userModel = new UserModel(1 , "Khaled Ahmed" ,
                "admin@orange.com" , "123456" , "01129788199" ,
                "ROLE_ADMIN");
        UserModel notExpectedUserModel = new UserModel(2 , "Khaled Mohamed" ,
                "1@2.com" , "123456" , "01129788199" ,
                "ROLE_ADMIN");
        Optional<UserModel> optionalUserModel = Optional.of(userModel);
        when(userRepository.findById(1)).thenReturn(optionalUserModel);
        Assert.assertNotEquals(userService.findById(1) , notExpectedUserModel.toDTO());
    }

    @Test
    public void saveValidTest() throws Exception {
        UserModel userModel = new UserModel(null , "Khaled Ahmed" ,
                "abcd@orange.com" , "123456" , "01111111111" ,
                "ROLE_USER");
        UserDRO userDRO = new UserDRO();
        userDRO.setEmail("abcd@orange.com");
        userDRO.setMobileNumber("01111111111");
        userDRO.setName("Khaled Ahmed");
        userDRO.setPassword("123456");

        when(userRepository.existsByEmail("abcd@orange.com")).thenReturn(false);
        when(userRepository.save(userModel)).thenReturn(userModel);
        Assert.assertEquals(userService.save(userDRO) , userModel.toDTO());
    }

    @Test(expected = Exception.class)
    public void saveExistingEmailTest() throws Exception {
        UserModel userModel = new UserModel(null , "Khaled Ahmed" ,
                "admin@orange.com" , "123456" , "01111111111" ,
                "ROLE_USER");
        UserDRO userDRO = new UserDRO();
        userDRO.setEmail("abcd@orange.com");
        userDRO.setMobileNumber("01111111111");
        userDRO.setName("Khaled Ahmed");
        userDRO.setPassword("123456");

        when(userRepository.existsByEmail("abcd@orange.com")).thenReturn(true);
        when(userRepository.save(userModel)).thenReturn(userModel);
        userService.save(userDRO);
    }

    @Test
    public void findByEmailValidTest() throws Exception{
        String email = "admin@orange.com";
        UserModel userModel = new UserModel(1 , "Khaled Ahmed" ,
                "admin@orange.com" , "123456" , "01129788199" ,
                "ROLE_ADMIN");
        Optional<UserModel> optionalUserModel = Optional.of(userModel);
        when(userRepository.findByEmail(email)).thenReturn(optionalUserModel);

        Assert.assertEquals(userService.findByemail(email) ,
                                userModel.toDTO());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void findByEmailNotValidTest() throws Exception{
        String email = "admin@orange.com";
        UserModel userModel = new UserModel(1 , "Khaled Ahmed" ,
                "admin@orange.com" , "123456" , "01129788199" ,
                "ROLE_ADMIN");
        Optional<UserModel> optionalUserModel = Optional.empty();
        when(userRepository.findByEmail(email)).thenReturn(optionalUserModel);

        userService.findByemail(email);
    }

}
