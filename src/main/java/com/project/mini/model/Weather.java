package com.project.mini.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private Float temp;
    private Float pressure;
    private Float humidity;
    private Float temp_min;
    private Float temp_max;
}
