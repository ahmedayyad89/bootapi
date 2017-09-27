package com.project.mini.controller;

import com.project.mini.dro.PredefinedNotesDRO;
import com.project.mini.dto.PredefinedNotesDTO;
import com.project.mini.service.PredefinedNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class PredefinedNotesController {
    @Autowired
    private PredefinedNotesService predefinedNotesService;


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/admin/predefnotes/get", method = {GET})
    public List<PredefinedNotesDTO> getAllPredefinedNotes() {
        return predefinedNotesService.getAllPredefniedNotes();
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/admin/predefnotes", method = {POST, PUT})
    public List<PredefinedNotesDTO> savePredefinedNotes(@RequestBody ArrayList<PredefinedNotesDRO> predfinedNotesDROS) {
        return predefinedNotesService.savePredfinedNotes(predfinedNotesDROS);
    }
}
