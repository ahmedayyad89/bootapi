package com.project.mini.service;


import com.project.mini.model.NoteModel;
import com.project.mini.model.WeatherContainerModel;
import com.project.mini.repository.NoteRepository;
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
    public NoteModel findByDate(Date dateId)
    {
        //find out if a note exists for that date
        Optional<NoteModel> noteOptional= noteRepository.findByDate(dateId);
        if(noteOptional.isPresent()) // A note does exist for that date, return it
        {
            return noteOptional.get();
        }
        else
        {
            //Make a new noteModel object, for today
            NoteModel noteModel = new NoteModel();
            noteModel.setDate(new Date(Calendar.getInstance().getTime().getTime()));
            //Get today's weatherModel, save it in the noteModel object
            RestTemplate restTemplate= new RestTemplate();
            WeatherContainerModel weatherContainerModel =  restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?id=360630&APPID=e847d37e15ad8c49264375cab68417f0", WeatherContainerModel.class);
            noteModel.setWeatherModel(weatherContainerModel.getMain());
            // get default predefined noteModel for today's temperature
            noteModel.setNote(preDefNoteService.get(1).getPredeNote(noteModel.getWeatherModel().getTemp()-(float)273.15));
            //save today's noteModel in the db for future retrieval
            noteModel = this.save(noteModel);

            return noteModel;
        }
    }
    public NoteModel save(NoteModel noteModel)
    {
        return noteRepository.save(noteModel);
    }
    public List<NoteModel> findAll()
    {
        return (List<NoteModel>) noteRepository.findAll();
    }
}
