package com.project.mini.service;

import com.project.mini.dro.PredfinedNotesDRO;
import com.project.mini.dto.PredfinedNotesDTO;
import com.project.mini.model.PredefinedNotesModel;
import com.project.mini.repository.PredefinedNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PredefinedNotesService {
    @Autowired
    PredefinedNoteRepository predefinedNoteRepository;

    public List<PredfinedNotesDTO> getAllPredfniedNotes() {
        return   predefinedNoteRepository.findAll()
                .stream()
                .map(predfniedNotes -> predfniedNotes.toDTO())
                .collect(Collectors.toList());
    }

    public List<PredfinedNotesDTO>
                savePredfinedNotes(List<PredfinedNotesDRO> predfinedNotesDRO) {
        List<PredefinedNotesModel> predefinedNotesModels =
                new ArrayList<>();
        for (PredfinedNotesDRO predfinedNoteDRO:
             predfinedNotesDRO) {
            PredefinedNotesModel predefinedNotesModel =
                    predefinedNoteRepository.findById(predfinedNoteDRO.getId());
            predefinedNotesModel.setMessage(predfinedNoteDRO.getMessage());
            predefinedNotesModels.add(predefinedNoteRepository.save(predefinedNotesModel));
        }
        return   predefinedNotesModels
                .stream()
                .map(predfniedNotes -> predfniedNotes.toDTO())
                .collect(Collectors.toList());
    }
    public String getPreefinedNote(double temperaure) {
        List<PredfinedNotesDTO> allPredefinedNotes = this.getAllPredfniedNotes();
        for (PredfinedNotesDTO note : allPredefinedNotes) {
            if(temperaure >= note.getMinimumTemperture() && note.getMaximumTemperture()> temperaure)
            {
                return  note.getMessage();
            }
        }
        return "RANGE NOT DEFINED";
    }

}
