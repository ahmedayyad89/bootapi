package com.project.mini.model;

import com.project.mini.dto.PredfinedNotesDTO;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "predefinednotes")
public class PredefinedNotesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String message;

    @Column
    private Double minimumTemperture;

    @Column
    private Double maximumTemperture;

    public PredfinedNotesDTO toDTO() {
        return new PredfinedNotesDTO(id , message , minimumTemperture , maximumTemperture);
    }
}
