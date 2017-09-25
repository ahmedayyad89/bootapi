package com.project.mini.controller;


import com.project.mini.dro.NoteDRO;
import com.project.mini.dto.NoteDTO;
import com.project.mini.model.PredefinedNotesModel;
import com.project.mini.service.NoteService;
import com.project.mini.service.PreDefNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

//@CrossOrigin(origins = {"http://localhost:3000"})
@CrossOrigin
@RestController

public class NoteController {
    @Autowired
    NoteService noteService;// noteRepository;

    @Autowired
    PreDefNoteService preDefNoteService;


    @Secured({"ROLE_ADMIN" , "ROLE_USER"})
    @RequestMapping(value= "/user/note/date", method = {GET})
    public NoteDTO get() {
        return noteService.findByDate(new Date(Calendar.getInstance().getTime().getTime()));
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value= "/admin/note/all", method = {GET})
    public List<NoteDTO> getAll() {
        return noteService.findAll();
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/admin/note/save", method = {POST,PUT})
    public NoteDTO save(@Valid @RequestBody NoteDRO noteDRO) {
        return noteService.save(noteDRO);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/admin/predefnotes/get", method = {GET})
    public PredefinedNotesModel getPreDefNotes() {
        return preDefNoteService.get(1);
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/admin/predefnotes", method = {POST, PUT})
    public PredefinedNotesModel savePreDefNotes(@RequestBody PredefinedNotesModel predefinedNoteModel) {
        predefinedNoteModel.setId(1);
        return preDefNoteService.save(predefinedNoteModel);
    }


}
