package com.project.mini.service;

import com.project.mini.model.PredefinedNotesModel;
import com.project.mini.repository.PredefinedNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PreDefNoteService {
    @Autowired
    PredefinedNoteRepository predefinedNoteRepository;

    public PredefinedNotesModel get(Integer id) {
        return predefinedNoteRepository.getById(id);
    }

    public PredefinedNotesModel save(PredefinedNotesModel predefinedNoteModel) {
        return predefinedNoteRepository.save(predefinedNoteModel);
    }

}
