package com.project.mini.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.project.mini.dro.PredefinedNotesDRO;
import com.project.mini.dto.PredefinedNotesDTO;
import com.project.mini.service.PredefinedNotesService;
import com.project.mini.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class PredefinedNotesControllerUnitTest {
    @Mock
    private PredefinedNotesService predefinedNotesService;

    @InjectMocks
    private PredefinedNotesController predefinedNotesController;

    private MockMvc mvc;
    private Gson gson;
    private List<PredefinedNotesDTO> predefinedNotesDTOList;
    private Type listOfPredefinedNoteDTOs;
    private Type listOfPredefinedNoteDROs;
    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(predefinedNotesController)
                .build();
        gson = new GsonBuilder().setDateFormat("yyyy mm dd").create();
        gson.serializeNulls();
        predefinedNotesDTOList = new ArrayList<PredefinedNotesDTO>(){{
            add(new PredefinedNotesDTO(1, "One", 1.0, 10.0));
            add(new PredefinedNotesDTO(2, "Two", 10.0, 15.0));
            add(new PredefinedNotesDTO(3, "Three", 15.0, 20.0));
            add(new PredefinedNotesDTO(4, "Four", 20.0, 70.0));
        }};

        listOfPredefinedNoteDTOs = new TypeToken<ArrayList<PredefinedNotesDTO>>(){}.getType();
        listOfPredefinedNoteDROs = new TypeToken<ArrayList<PredefinedNotesDRO>>(){}.getType();
    }

    @Test
    public void testGetAllPredefinedNotes() throws Exception {

        when(predefinedNotesService.getAllPredefniedNotes()).thenReturn(predefinedNotesDTOList);

        ResultActions resultActions = mvc.perform(get("/admin/predefnotes/get"))
                .andExpect(status().isOk());

        Assert.assertEquals(resultActions.andReturn().getResponse().getContentAsString()
                , gson.toJson(predefinedNotesDTOList, listOfPredefinedNoteDTOs));

    }

    @Test
    public void testSavePredefinedNotes() throws Exception {

        List<PredefinedNotesDRO> predefinedNotesDROList= new ArrayList<PredefinedNotesDRO>(){{
            add(new PredefinedNotesDRO(1, "One"));
            add(new PredefinedNotesDRO(2, "Two"));
            add(new PredefinedNotesDRO(3, "Three"));
            add(new PredefinedNotesDRO(4, "Four"));
        }};


        when(predefinedNotesService.savePredfinedNotes(any(List.class))).thenReturn(predefinedNotesDTOList);


        ResultActions resultActions = mvc.perform(post("/admin/predefnotes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(gson.toJson(predefinedNotesDROList,listOfPredefinedNoteDROs))
        )
                .andExpect(status().isOk());

        Assert.assertEquals(resultActions.andReturn().getResponse().getContentAsString()
                , gson.toJson(predefinedNotesDTOList, listOfPredefinedNoteDTOs));

    }
}
