package com.project.mini.service;

import com.project.mini.dto.NoteDTO;
import com.project.mini.model.NoteModel;
import com.project.mini.repository.NoteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class NoteServiceUnitTest {
    @Mock NoteRepository  noteRepository;
    @InjectMocks  NoteService noteService;

    @Test
    public void findByDateValidTest(){

        NoteDTO expectedNoteDTO = new NoteDTO(new Date(2017,9,25), "", null);
        NoteModel expectedNoteModel = new NoteModel(new Date(2017,9,25), "", null);
        Optional<NoteModel> expectedNoteModelOptional = Optional.of(expectedNoteModel);
        //Expectations
        when(noteRepository.findByDate(any(Date.class))).thenReturn(expectedNoteModelOptional);


        Assert.assertEquals(noteService.findByDate(new Date(2017,9,25)),
                expectedNoteDTO);
    }
}
