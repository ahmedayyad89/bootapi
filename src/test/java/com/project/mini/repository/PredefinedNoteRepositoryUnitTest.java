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

    @Test
    public void getAllPredefinedNotesTest () {
        List<PredefinedNotesModel> predfinedNotesDTOList = predefinedNoteRepository.findAll();
        Assert.assertEquals(predfinedNotesDTOList.getClass(), ArrayList.class);
    }

}
