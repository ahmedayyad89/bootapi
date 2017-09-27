package com.project.mini.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.project.mini.dro.NoteDRO;
import com.project.mini.dto.NoteDTO;
import com.project.mini.model.WeatherModel;
import com.project.mini.service.NoteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.rest.webmvc.json.JacksonSerializers;
import org.springframework.http.MediaType;
import org.springframework.security.access.method.P;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class NoteControllerUnitTests {
    @Mock
    private NoteService noteService ;
    @InjectMocks
    private NoteController noteController;

    private MockMvc mvc;
    private Gson gson;


    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(noteController)
                .build();
        gson = new GsonBuilder().setDateFormat("yyyy mm dd").create();
        gson.serializeNulls();
    }

    @Test
    public void testFindNoteByDate() throws Exception {
        NoteDTO noteDTO = new NoteDTO(new Date(117,9,25), "note", new WeatherModel());

        when(noteService.findByDate(any(Date.class))).thenReturn(noteDTO);
        ResultActions  resultActions= mvc.perform(get("/user/note/date")).andExpect(status().isOk());
        Assert.assertEquals(gson.toJson(gson.fromJson(resultActions.andReturn().getResponse().getContentAsString() ,
                        NoteDTO.class)), gson.toJson(noteDTO));
    }

    @Test
    public void testGetAll() throws Exception {

        List<NoteDTO> noteDTOList= new ArrayList<NoteDTO>(){{
            new NoteDTO(new Date(117,9,25), "note", new WeatherModel());
            new NoteDTO(new Date(117,9,26), "note", new WeatherModel());
            new NoteDTO(new Date(117,9,27), "note", new WeatherModel());
        }};


        Type listOfNoteDTO = new TypeToken<ArrayList<NoteDTO>>(){}.getType();
        when(noteService.findAll()).thenReturn(noteDTOList);


        ResultActions  resultActions= mvc.perform(get("/admin/note/all")).andExpect(status().isOk());

        Assert.assertEquals(gson.toJson(gson.fromJson(resultActions.andReturn().getResponse().getContentAsString() ,
                listOfNoteDTO),listOfNoteDTO), gson.toJson(noteDTOList,listOfNoteDTO));
    }

    @Test
    public void testSave() throws Exception {
        NoteDTO noteDTO = new NoteDTO(new Date(117, 9,25), "note", new WeatherModel());
        NoteDRO noteDRO = new NoteDRO();
        noteDRO.setNote("newnote");
        when(noteService.save(any(NoteDRO.class))).thenReturn(noteDTO);
        ResultActions  resultActions= mvc.perform(post("/admin/note/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(noteDRO)))
                .andExpect(status().isOk());
        Assert.assertEquals(gson.toJson(
                gson.fromJson(resultActions.andReturn().getResponse().getContentAsString(), NoteDTO.class)
                ,NoteDTO.class),
                gson.toJson(noteDTO, NoteDTO.class));
    }

    @Test
    public void testSaveNotValid() throws Exception {
        NoteDTO noteDTO = new NoteDTO(new Date(117, 9,25), "note", new WeatherModel());
        NoteDRO noteDRO = new NoteDRO();
        when(noteService.save(any(NoteDRO.class))).thenReturn(noteDTO);
        ResultActions  resultActions= mvc.perform(post("/admin/note/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(noteDRO)))
                .andExpect(status().is(400));

    }


}
