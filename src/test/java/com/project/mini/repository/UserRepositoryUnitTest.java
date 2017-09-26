package com.project.mini.repository;

import com.project.mini.model.UserModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryUnitTest {
    @Autowired
    private UserRepository userRepository;
    private final Integer USER_ID = 1;
    private final Integer USER_ID_NOT_VALID = 11;
    private final String USER_EMAIL_NOT_VALID = "1@2.3" ;
    private final String USER_EMAIL = "admin@orange.com" ;


    @Test
    public void findByIdTestValid() {
        Assert.assertTrue(
                userRepository.findById(USER_ID).isPresent()
        );
    }

    @Test
    public void findByIdTestNotValid() {
        Assert.assertFalse(
                userRepository.findById(USER_ID_NOT_VALID).isPresent()
        );
    }

    @Test
    public void findByEmailValid() {
        Assert.assertTrue(
                userRepository.findByEmail(USER_EMAIL).isPresent()
        );
    }

    @Test
    public void findByEmailNotValid() {
        Assert.assertFalse(
                userRepository.findByEmail(USER_EMAIL_NOT_VALID).isPresent()
        );
    }

    @Test
    public void existsByEmailValid() {
        Assert.assertTrue(
                userRepository.existsByEmail(USER_EMAIL)
        );
    }

    @Test
    public void existsByEmailNotValid() {
        Assert.assertFalse(
                userRepository.existsByEmail(USER_EMAIL_NOT_VALID)
        );
    }
}

