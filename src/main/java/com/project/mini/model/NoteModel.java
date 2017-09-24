package com.project.mini.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "note")
public class NoteModel {

    @Id
    private Date date;

    @Column
    private String note;

    @Embedded
    private WeatherModel weatherModel;
}


