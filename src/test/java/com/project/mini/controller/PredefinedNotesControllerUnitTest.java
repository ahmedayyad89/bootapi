package com.project.mini.controller;

import com.project.mini.service.PredefinedNotesService;
import com.project.mini.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class PredefinedNotesControllerUnitTest {
    @Mock
    private PredefinedNotesService predefinedNotesService;

    @InjectMocks
    private PredefinedNotesController predefinedNotesController;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(predefinedNotesController)
                .build();
    }
}
