package com.project.mini.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class PredefinedNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String oneTo10;
    private String tenTo15;
    private String fifteenTo20;
    private String moreThat20;
    public String getPredeNote(Float temperature )
    {
        if(temperature<10.0)
        {
            return oneTo10;
        }
        if(10.0<=temperature && temperature<15.0)
        {
            return tenTo15;
        }
        if(15.0<=temperature && temperature<20.0)
        {
            return fifteenTo20;
        }
        if(20.0<=temperature)
        {
            return moreThat20;
        }
        return "";
    }
}
