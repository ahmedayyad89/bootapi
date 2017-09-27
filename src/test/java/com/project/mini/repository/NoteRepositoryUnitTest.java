package com.project.mini.repository;


import com.project.mini.model.NoteModel;
import com.project.mini.repository.NoteRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteRepositoryUnitTest {
    @Autowired
    private NoteRepository noteRepository;

    @Before
    public void setUp() {
        NoteModel note = new NoteModel(new Date(Calendar.getInstance().getTime().getTime()) ,
                            "Hot" , null);
        noteRepository.save(note);
    }

    @Test
    public void findNoteByDateTestExist() {
        Assert.assertTrue(
                noteRepository.findByDate(new Date(Calendar.getInstance().getTime().getTime())).isPresent()
        );
    }



    @Test
    public void findNoteByDateTestNotExist() {
        Assert.assertFalse(
                noteRepository.findByDate(new Date(2017 , 9 , 27))
                        .isPresent()
        );
    }

    @After
    public void tearDown() {
        noteRepository.delete(new Date(Calendar.getInstance().getTime().getTime()));
    }
}
