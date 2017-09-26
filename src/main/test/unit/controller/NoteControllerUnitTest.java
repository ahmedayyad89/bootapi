package unit.controller;


import com.project.mini.controller.NoteController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NoteControllerUnitTest {
    @Autowired
    private NoteController noteController;


    @Test
    public void findNoteByDateTestValid() {
        Assert.assertEquals(
           noteController.findNoteByDate().getNote() ,
                null
        );
    }
}
