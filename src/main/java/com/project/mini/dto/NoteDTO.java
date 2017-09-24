package com.project.mini.dto;

import com.project.mini.model.WeatherModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import java.sql.Date;

@Data
@AllArgsConstructor
public class NoteDTO {
    private Date date;

    private String note;

    private WeatherModel weatherModel;
}
