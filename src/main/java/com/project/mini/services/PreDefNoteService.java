package com.project.mini.services;

import com.project.mini.model.PredefinedNotes;
import com.project.mini.repos.PredefinedNotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PreDefNoteService {
    @Autowired
    PredefinedNotesRepository pDNRepository;


    //PredefinedNotes predefinedNotes;

    public PredefinedNotes get(Integer id) {
        return(PredefinedNotes) pDNRepository.getById(id);
    }

    public PredefinedNotes save(PredefinedNotes predefinedNotes) {
        return pDNRepository.save(predefinedNotes);
    }
}
