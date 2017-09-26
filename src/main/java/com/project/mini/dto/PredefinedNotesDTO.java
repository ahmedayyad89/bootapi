package com.project.mini.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PredefinedNotesDTO {
    private Integer id;
    private String message;
    private Double minimumTemperture;
    private Double maximumTemperture;
}
