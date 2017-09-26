package com.project.mini.repository;


import com.project.mini.model.PredefinedNotesModel;
import jdk.nashorn.internal.AssertsEnabled;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PredefinedNoteRepositoryUnitTest {
    @Autowired
    PredefinedNoteRepository predefinedNoteRepository;

    private final Integer PREDEFINED_NOTE_ID = 1;
    private final Integer PREDEFINED_NOTE_ID_NOT_VALID = 5;
    private final String DEFAULT_MESSAGE = "";
    private final String DEFAULT_MESSAGE_NOT_VALID = "Not valid";
    private final Integer INDEX = 0;
    @Test
    public void getAllPredefinedNotesTestValid () {
        List<PredefinedNotesModel> predfinedNotesDTOList = predefinedNoteRepository.findAll();

        Assert.assertTrue(predfinedNotesDTOList.size() > 0);
        Assert.assertEquals(predfinedNotesDTOList.get(INDEX).getMessage(),
                DEFAULT_MESSAGE);
    }

    @Test
    public void getAllPredefinedNotesTestNotValid () {
        List<PredefinedNotesModel> predfinedNotesDTOList = predefinedNoteRepository.findAll();

        Assert.assertFalse(predfinedNotesDTOList.size() == 0);
        Assert.assertNotEquals(predfinedNotesDTOList.get(INDEX).getMessage(),
                DEFAULT_MESSAGE_NOT_VALID);
    }

    @Test
    public void findByIdTestValid() {
        Assert.assertTrue(
                predefinedNoteRepository.findById(PREDEFINED_NOTE_ID).isPresent()
        );
    }

    @Test
    public void findByIdTestNotValid() {
        Assert.assertFalse(
                predefinedNoteRepository.findById(PREDEFINED_NOTE_ID_NOT_VALID).isPresent()
        );
    }

}
