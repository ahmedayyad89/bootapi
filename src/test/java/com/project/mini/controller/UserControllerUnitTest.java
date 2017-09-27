package com.project.mini.controller;

import com.project.mini.dto.UserDTO;
import com.project.mini.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CorsFilter;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerUnitTest {
    @Mock
    private UserService userService;

    @Autowired
    private WebApplicationContext context;

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
    public void foo() throws Exception {
        UserDTO userDTO = new UserDTO("khaled" , "admin@orange.com" ,
                    "01111111111" , "ROLE_ADMIN");
        when(userService.findByemail("admin@orange.com")).thenReturn(userDTO);
        ResultActions resultActions = mvc.perform(get("/user/login"))
                .andExpect(status().isOk());
        System.out.println(userService.findByemail("admin@orange.com"));
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }
}
