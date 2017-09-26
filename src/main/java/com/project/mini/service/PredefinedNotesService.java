package com.project.mini.service;

import com.project.mini.dro.PredefinedNotesDRO;
import com.project.mini.dto.PredefinedNotesDTO;
import com.project.mini.model.PredefinedNotesModel;
import com.project.mini.repository.PredefinedNotesRepository;
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
    PredefinedNotesRepository predefinedNoteRepository;

    public List<PredefinedNotesDTO> getAllPredefniedNotes() {
        return   predefinedNoteRepository.findAll()
                .stream()
                .map(predfniedNotes -> predfniedNotes.toDTO())
                .collect(Collectors.toList());
    }

    public List<PredefinedNotesDTO>
                savePredfinedNotes(List<PredefinedNotesDRO> predfinedNotesDRO) {
        List<PredefinedNotesModel> predefinedNotesModels =
                new ArrayList<>();
        for (PredefinedNotesDRO predfinedNoteDRO:
             predfinedNotesDRO) {
            PredefinedNotesModel predefinedNotesModel =
                    predefinedNoteRepository.findById(predfinedNoteDRO.getId()).get();
            predefinedNotesModel.setMessage(predfinedNoteDRO.getMessage());
            predefinedNotesModels.add(predefinedNoteRepository.save(predefinedNotesModel));
        }
        return   predefinedNotesModels
                .stream()
                .map(predfniedNotes -> predfniedNotes.toDTO())
                .collect(Collectors.toList());
    }
    public String getPredefinedNote(double temperature) {
        List<PredefinedNotesDTO> allPredefinedNotes = this.getAllPredefniedNotes();
        for (PredefinedNotesDTO note : allPredefinedNotes) {
            if(temperature >= note.getMinimumTemperture() &&
                    note.getMaximumTemperture()> temperature) {
                return  note.getMessage();
            }
        }
        return "RANGE NOT DEFINED";
    }

}
