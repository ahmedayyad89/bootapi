package com.project.mini.service;


import com.project.mini.dro.NoteDRO;
import com.project.mini.dto.NoteDTO;
import com.project.mini.model.NoteModel;
import com.project.mini.model.WeatherContainerModel;
import com.project.mini.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoteService {
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    PredefinedNotesService preDefNoteService;
    public NoteDTO findByDate(Date dateId)
    {
        //find out if a note exists for that date
        Optional<NoteModel> noteOptional= noteRepository.findByDate(dateId);
        if(noteOptional.isPresent()){ // A note does exist for that date, return it{
            NoteModel noteModel =
                    noteOptional.get();
            return new NoteDTO(noteModel.getDate() , noteModel.getNote() , noteModel.getWeatherModel());
        }
        else
        {
            //Make a new noteModel object, for today
            NoteModel noteModel = new NoteModel(null , null , null);
            noteModel.setDate(new Date(Calendar.getInstance().getTime().getTime()));
            //Get today's weatherModel, save it in the noteModel object
            RestTemplate restTemplate= new RestTemplate();
            WeatherContainerModel weatherContainerModel =  restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?id=360630&APPID=e847d37e15ad8c49264375cab68417f0", WeatherContainerModel.class);
            noteModel.setWeatherModel(weatherContainerModel.getMain());
            // get default predefined noteModel for today's temperature
//            noteModel.setNote(preDefNoteService.get(1).getPredeNote(noteModel.getWeatherModel().getTemp()-(float)273.15));
            //save today's noteModel in the db for future retrieval
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
