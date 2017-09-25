package com.project.mini.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PredfinedNotesDTO {
    private Integer id;
    private String message;
    private Double minimumTemperture;
    private Double maximumTemperture;
}
