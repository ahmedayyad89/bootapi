package com.project.mini.services;


import com.project.mini.model.Note;
import com.project.mini.model.WeatherContainer;
import com.project.mini.repos.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteService {
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    PreDefNoteService preDefNoteService;
    public Note findByDate(Date dateId)
    {
        //find out if a note exists for that date
        Optional<Note> noteOptional= noteRepository.findByDate(dateId);
        if(noteOptional.isPresent()) // A note does exist for that date, return it
        {
            return noteOptional.get();
        }
        else
        {
            //Make a new note object, for today
            Note note = new Note ();
            note.setDate(new Date(Calendar.getInstance().getTime().getTime()));
            //Get today's weather, save it in the note object
            RestTemplate restTemplate= new RestTemplate();
            WeatherContainer weatherContainer=  restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?id=360630&appid=e847d37e15ad8c49264375cab68417f0", WeatherContainer.class);
            note.setWeather(weatherContainer.getMain());
            // get default predefined note for today's temperature
            note.setNote(preDefNoteService.get(1).getPredeNote(note.getWeather().getTemp()-(float)273.15));
            //save today's note in the db for future retrieval
            note = this.save(note);

            return note;
        }
    }
    public Note save(Note note)
    {
        return noteRepository.save(note);
    }
    public List<Note> findAll()
    {
        return (List<Note>) noteRepository.findAll();
    }
}
