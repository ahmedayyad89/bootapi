package com.project.mini.model;

import com.project.mini.dto.PredefinedNotesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "predefinednotes")
@AllArgsConstructor
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

    public PredefinedNotesDTO toDTO() {
        return new PredefinedNotesDTO(id , message , minimumTemperture , maximumTemperture);
    }

    public PredefinedNotesModel(){}
}
