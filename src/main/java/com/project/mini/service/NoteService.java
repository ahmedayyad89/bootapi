package com.project.mini.service;


import com.project.mini.dro.NoteDRO;
import com.project.mini.dto.NoteDTO;
import com.project.mini.model.NoteModel;
import com.project.mini.repository.NoteRepository;
import com.project.mini.support.WeatherFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@PropertySource("classpath:application.yml")
public class NoteService {
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    PredefinedNotesService predefinedNotesService;

    public NoteDTO findByDate(Date dateId) {
        //find out if a note exists for that date
        Optional<NoteModel> noteOptional= noteRepository.findByDate(dateId);
        if(noteOptional.isPresent()){ // A note does exist for that date, return it
            NoteModel noteModel =
                    noteOptional.get();
            return new NoteDTO(noteModel.getDate() , noteModel.getNote() , noteModel.getWeatherModel());
        } else {
            NoteModel noteModel = new NoteModel(null , null , null);
            noteModel.setDate(new Date(Calendar.getInstance().getTime().getTime()));
            noteModel.setWeatherModel(WeatherFactory.getWeather());
            noteModel.setNote(predefinedNotesService.getPredefinedNote(noteModel.getWeatherModel().getTemp()));
            noteModel = noteRepository.save(noteModel);
            return new NoteDTO(noteModel.getDate() , noteModel.getNote() , noteModel.getWeatherModel());
        }
    }
    public NoteDTO save(NoteDRO noteDRO) {
        NoteDTO noteDTO = findByDate(new Date(Calendar.getInstance().getTime().getTime()));
        NoteModel noteModel = new NoteModel(noteDTO.getDate() , noteDRO.getNote() ,
                noteDTO.getWeatherModel());
        noteModel = noteRepository.save(noteModel);
        return new NoteDTO(noteModel.getDate() , noteModel.getNote() , noteModel.getWeatherModel());
    }
    public List<NoteDTO> findAll() {
        ArrayList<NoteModel> notes = (ArrayList<NoteModel>)noteRepository.findAll();
        return  notes.stream().map(note -> note.toDTO()).collect(Collectors.toList());
    }
}
