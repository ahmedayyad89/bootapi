package com.project.mini.controllers;


import com.project.mini.model.PredefinedNotes;
import com.project.mini.services.NoteService;
import com.project.mini.model.Note;
import com.project.mini.services.PreDefNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class NoteController {
    @Autowired
    NoteService noteService;// noteRepository;

    @Autowired
    PreDefNoteService preDefNoteService;


    @RequestMapping(value= "/user/note/date", method = {GET})
    public Note get()
    {
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        return noteService.findByDate(date);
    }


    @RequestMapping(value= "/admin/note/all", method = {GET})
    public List<Note> getAll()
    {
        return noteService.findAll();
    }


    @RequestMapping(value = "/admin/note/save", method = {POST,PUT})
    public Note save(@RequestBody Note note )
    {

        Note newNote = noteService.findByDate(new Date(Calendar.getInstance().getTime().getTime()));
        note.setWeather(newNote.getWeather());
        return noteService.save(note);
    }


    @RequestMapping(value = "/admin/predefnotes/get", method = {GET})
    public PredefinedNotes getPreDefNotes()
    {
        return preDefNoteService.get(1);
    }


    @RequestMapping(value = "/admin/predefnotes", method = {POST, PUT})
    public PredefinedNotes savePreDefNotes(@RequestBody PredefinedNotes predefinedNotes)
    {
        predefinedNotes.setId(1);
        return preDefNoteService.save(predefinedNotes);
    }


}
