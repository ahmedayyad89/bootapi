package com.project.mini.service;

import com.project.mini.dto.PredefinedNotesDTO;
import com.project.mini.model.PredefinedNotesModel;
import com.project.mini.repository.PredefinedNotesRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PredefinedNoteServiceUnitTest {
    @Mock
    private PredefinedNotesRepository predefinedNotesRepository;

    @InjectMocks
    private PredefinedNotesService predefinedNotesService;


    @Test
    public void getAllPredefinedNotesValidTest() {
        List<PredefinedNotesModel> returned =
                new ArrayList<>();
        List<PredefinedNotesDTO> actual = returned
                                        .stream()
                                        .map(predfniedNotes -> predfniedNotes.toDTO())
                                        .collect(Collectors.toList());
        when(predefinedNotesRepository.findAll()).thenReturn(returned);
        Assert.assertEquals(predefinedNotesService.getAllPredefniedNotes() ,
               actual );
    }
    @Test
    public void getAllPredefinedNotesNotValidTest() {
        List<PredefinedNotesModel> returned =
                new ArrayList<>();
        List<PredefinedNotesDTO> actual = returned
                .stream()
                .map(predfniedNotes -> predfniedNotes.toDTO())
                .collect(Collectors.toList());
        returned.add(new PredefinedNotesModel());
        when(predefinedNotesRepository.findAll()).thenReturn(returned);
        Assert.assertNotEquals(predefinedNotesService.getAllPredefniedNotes() ,
                actual );
    }



}
