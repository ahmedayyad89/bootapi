package com.project.mini.service;

import com.project.mini.model.PredefinedNoteModel;
import com.project.mini.repository.PredefinedNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PreDefNoteService {
    @Autowired
    PredefinedNoteRepository pDNRepository;

    public PredefinedNoteModel get(Integer id) {
        return pDNRepository.getById(id);
    }

    public PredefinedNoteModel save(PredefinedNoteModel predefinedNoteModel) {
        return pDNRepository.save(predefinedNoteModel);
    }

}
