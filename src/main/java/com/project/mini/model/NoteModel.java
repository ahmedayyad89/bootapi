package com.project.mini.model;

import com.project.mini.dto.NoteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "note")
@AllArgsConstructor
public class NoteModel {

    @Id
    private Date date;

    @Column
    private String note;

    @Embedded
    private WeatherModel weatherModel;

    public NoteDTO toDTO() {
        NoteDTO noteDTO = new NoteDTO(date, note,weatherModel);
        return noteDTO;
    }
    public NoteModel(){

    }
}


