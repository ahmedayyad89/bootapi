package com.project.mini.service;

import com.project.mini.dro.NoteDRO;
import com.project.mini.dto.NoteDTO;
import com.project.mini.model.NoteModel;

import com.project.mini.model.WeatherModel;
import com.project.mini.repository.NoteRepository;
import com.project.mini.support.WeatherFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(WeatherFactory.class)
public class NoteServiceUnitTest {
    @Mock NoteRepository  noteRepository;
    @Mock PredefinedNotesService predefinedNotesService;
    @InjectMocks  NoteService noteService;

    @Test
    public void findByDateValidTest(){

        NoteDTO expectedNoteDTO = new NoteDTO(new Date(2017,9,25), "", null);
        NoteModel expectedNoteModel = new NoteModel(new Date(2017,9,25), "", null);
        Optional<NoteModel> expectedNoteModelOptional = Optional.of(expectedNoteModel);
        when(noteRepository.findByDate(any(Date.class))).thenReturn(expectedNoteModelOptional);

        Assert.assertEquals(noteService.findByDate(new Date(2017,9,25)),
                expectedNoteDTO);
    }

    @Test
    public void findByDateNotValidTest(){

        NoteDTO expectedNoteDTO = new NoteDTO(new Date(2017,9,25), "", null);
        NoteModel expectedNoteModel = new NoteModel(new Date(2017,9,25), "", null);
        Optional<NoteModel> expectedNoteModelOptional = Optional.ofNullable(null);
        PowerMockito.mockStatic(WeatherFactory.class);
        WeatherModel expectedWeather = new WeatherModel();
        expectedWeather.setTemp(5.0f);
        //Expectations
        when(noteRepository.findByDate(any(Date.class))).thenReturn(expectedNoteModelOptional);
        PowerMockito.when(WeatherFactory.getWeather()).thenReturn(expectedWeather);
        when(predefinedNotesService.getPredefinedNote(any(Float.class))).thenReturn("");
        when(noteRepository.save(any(NoteModel.class))).thenReturn(expectedNoteModel);

        Assert.assertEquals(noteService.findByDate(new Date(2017,9,25)),
                expectedNoteDTO);
    }

    @Test
    public void saveTest()
    {
        NoteDTO expectedNoteDTO = new NoteDTO(new Date(2017,9,25), "NewNote", null );
        NoteModel middleNoteModel = new NoteModel(new Date(2017,9,25), "" , null);

        NoteDRO inputNoteDRO = new NoteDRO();
        inputNoteDRO.setNote("NewNote");
        when(noteRepository.findByDate(any(Date.class))).thenReturn(Optional.of(middleNoteModel));
        when(noteRepository.save(any(NoteModel.class))).thenReturn(new NoteModel(new Date(2017,9,25),
                inputNoteDRO.getNote() , null));

        Assert.assertEquals(noteService.save(inputNoteDRO), expectedNoteDTO);

    }

    @Test
    public void findAllTest()
    {
        when(noteRepository.findAll()).thenReturn(new ArrayList<NoteModel>(){
            {
                add(new NoteModel(new Date(2017,9,25), "", null));
                add(new NoteModel(new Date(2017,9,26), "", null));
                add(new NoteModel(new Date(2017,9,27), "", null));
            }
        });
        Assert.assertEquals(noteService.findAll(), new ArrayList<NoteDTO>(){{
            add(new NoteDTO(new Date(2017,9,25), "", null));
            add(new NoteDTO(new Date(2017,9,26), "", null));
            add(new NoteDTO(new Date(2017,9,27), "", null));

        }});
    }
}
