package com.project.mini.controller;

import com.project.mini.dro.PredfinedNotesDRO;
import com.project.mini.dto.PredfinedNotesDTO;
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
public class PredfinedNotesController {
    @Autowired
    private PredefinedNotesService predefinedNotesService;


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/admin/predefnotes/get", method = {GET})
    public List<PredfinedNotesDTO> getAllPredfinedNotes() {
        return predefinedNotesService.getAllPredfniedNotes();
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/admin/predefnotes", method = {POST, PUT})
    public List<PredfinedNotesDTO> savePredfinedNotes(@RequestBody ArrayList<PredfinedNotesDRO> predfinedNotesDROS) {
        System.out.println(predfinedNotesDROS.size());
        return predefinedNotesService.savePredfinedNotes(predfinedNotesDROS);
    }
}
