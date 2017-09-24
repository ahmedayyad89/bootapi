package com.project.mini.controller;


import com.project.mini.model.NoteModel;
import com.project.mini.model.PredefinedNoteModel;
import com.project.mini.service.NoteService;
import com.project.mini.service.PreDefNoteService;
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
    public NoteModel get()
    {
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        return noteService.findByDate(date);
    }


    @RequestMapping(value= "/admin/note/all", method = {GET})
    public List<NoteModel> getAll()
    {
        return noteService.findAll();
    }


    @RequestMapping(value = "/admin/note/save", method = {POST,PUT})
    public NoteModel save(@RequestBody NoteModel noteModel)
    {

        NoteModel newNoteModel = noteService.findByDate(new Date(Calendar.getInstance().getTime().getTime()));
        noteModel.setWeatherModel(newNoteModel.getWeatherModel());
        return noteService.save(noteModel);
    }


    @RequestMapping(value = "/admin/predefnotes/get", method = {GET})
    public PredefinedNoteModel getPreDefNotes()
    {
        return preDefNoteService.get(1);
    }


    @RequestMapping(value = "/admin/predefnotes", method = {POST, PUT})
    public PredefinedNoteModel savePreDefNotes(@RequestBody PredefinedNoteModel predefinedNoteModel)
    {
        predefinedNoteModel.setId(1);
        return preDefNoteService.save(predefinedNoteModel);
    }


}
