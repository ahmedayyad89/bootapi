package com.project.mini.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Note {

    @Id
    private Date date;

    @Column
    private String note;

    @Embedded
    private Weather weather;
}


