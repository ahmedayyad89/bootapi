package com.project.mini.controller;

import com.google.gson.Gson;
import com.project.mini.dro.UserDRO;
import com.project.mini.dto.UserDTO;
import com.project.mini.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class UserControllerUnitTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    @WithMockUser(username = "admin@orange.com" , password = "123456")
    public void loginTestValid() throws Exception {
        UserDTO userDTO = new UserDTO("khaled" , "admin@orange.com" ,
                    "01111111111" , "ROLE_ADMIN");
        when(userService.findByemail("admin@orange.com")).thenReturn(userDTO);
        ResultActions resultActions = mvc.perform(get("/user/login"))
                .andExpect(status().isOk());
        Assert.assertEquals(resultActions.andReturn().getResponse().getContentAsString() ,
        new Gson().toJson(userDTO));
    }

    @Test
    @WithMockUser(username = "admin@orange.com" , password = "123456")
    public void loginTestNotValid() throws Exception {
        UserDTO userDTO = new UserDTO("khaled" , "abcd@orange.com" ,
                "01111111111" , "ROLE_ADMIN");
        when(userService.findByemail("abcd@orange.com")).thenReturn(userDTO);
        ResultActions resultActions = mvc.perform(get("/user/login"))
                .andExpect(status().isOk());
        Assert.assertEquals(resultActions.andReturn().getResponse().getContentAsString() ,
                "");
    }

    @Test
    public void logoutTestValid() throws Exception {
        SecurityContextHolder
                .getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken
                        ("admin@orange.com", "123456"));
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        mvc.perform(get("/user/logout")).andExpect(status().isOk());
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        Assert.assertNull(authentication);
    }

    @Test
    public void saveTestValid() throws Exception {
        UserDRO userDRO = new UserDRO();
        userDRO.setPassword("123456");
        userDRO.setName("Khaled");
        userDRO.setMobileNumber("01234567891");
        userDRO.setEmail("admin@orange.com");

        UserDTO userDTO = new UserDTO("Khaled" , "admin@orange.com" ,
                "01234567891" , "ROLE_USER");

        when(userService.save(userDRO)).thenReturn(userDTO);
        ResultActions resultActions = mvc.perform(post("/save/user")
                .contentType(APPLICATION_JSON_UTF8)
                .content(new Gson().toJson(userDRO)))
                .andExpect(status().isOk());
        Assert.assertEquals(resultActions.andReturn().getResponse().getContentAsString() ,
                new Gson().toJson(userDTO));
    }


    @Test
    public void saveTestNotValid() throws Exception {
        UserDRO userDRO = new UserDRO();
        userDRO.setPassword("12345");
        userDRO.setName("Khaled");
        userDRO.setMobileNumber("01234567891");
        userDRO.setEmail("ad");

        UserDTO userDTO = new UserDTO("Khaled" , "ad" ,
                "01234567891" , "ROLE_USER");

        when(userService.save(userDRO)).thenReturn(userDTO);
        mvc.perform(post("/save/user")
                .contentType(APPLICATION_JSON_UTF8)
                .content(new Gson().toJson(userDRO)))
                .andExpect(status().is(400));
    }
}
