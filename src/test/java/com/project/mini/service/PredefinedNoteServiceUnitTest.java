package com.project.mini.service;

import com.project.mini.dro.PredefinedNotesDRO;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Matchers.any;
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
        List<PredefinedNotesDTO> actual
                = returned
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
                .map(predefniedNotes -> predefniedNotes.toDTO())
                .collect(Collectors.toList());
        returned.add(new PredefinedNotesModel());
        when(predefinedNotesRepository.findAll()).thenReturn(returned);
        Assert.assertNotEquals(predefinedNotesService.getAllPredefniedNotes() ,
                actual );
    }

    @Test
    public void savePredefinedNotes() {
        List<PredefinedNotesDRO> predefinedNotesDROList  =
                new ArrayList<PredefinedNotesDRO>();
        PredefinedNotesDRO predefinedNotesDRO =
                new PredefinedNotesDRO();
        predefinedNotesDRO.setId(1);
        predefinedNotesDRO.setMessage("Note1");
        predefinedNotesDROList.add(predefinedNotesDRO);

        List<PredefinedNotesDTO> predefinedNotesDTOList =
                new ArrayList<PredefinedNotesDTO>();
        predefinedNotesDTOList.add(new PredefinedNotesDTO(1, "Note1",
                1.0, 10.0));

        PredefinedNotesModel middlePredefinedNotesModel = new PredefinedNotesModel();
        middlePredefinedNotesModel.setId(1);
        middlePredefinedNotesModel.setMessage("");
        middlePredefinedNotesModel.setMaximumTemperature(10.0);
        middlePredefinedNotesModel.setMinimumTemperature(1.0);
        when(predefinedNotesRepository.findById(1)).thenReturn(Optional.of(middlePredefinedNotesModel));

        PredefinedNotesModel predefinedNotesModel = new PredefinedNotesModel();
        predefinedNotesModel.setId(1);
        predefinedNotesModel.setMessage("Note1");
        predefinedNotesModel.setMaximumTemperature(10.0);
        predefinedNotesModel.setMinimumTemperature(1.0);
        when(predefinedNotesRepository.save(any(PredefinedNotesModel.class)))
                .thenReturn(predefinedNotesModel);



        Assert.assertEquals(predefinedNotesService.savePredefinedNotes(predefinedNotesDROList),predefinedNotesDTOList);
    }

    @Test
    public void getPredefinedNoteTest(){
        when(predefinedNotesRepository.findAll()).thenReturn(
                new ArrayList<PredefinedNotesModel>(){{
                    add(new PredefinedNotesModel(1,"One", 1.0,10.0));
                    add(new PredefinedNotesModel(2,"Two", 10.0,15.0));
                    add(new PredefinedNotesModel(3,"Three", 15.0,20.0));
                    add(new PredefinedNotesModel(4,"Four", 20.0,70.0));
                }}
        );
        Assert.assertEquals(predefinedNotesService.getPredefinedNote(5.0), "One");
    }

}
