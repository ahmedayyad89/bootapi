package com.project.mini.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherModel {
    @JsonIgnore
    @Transient
    final Float KELVIN_CORRECTION_VALUE = 273.15f;
    private Float temp;
    private Float pressure;
    private Float humidity;
    private Float temp_min;
    private Float temp_max;

    public void setTemp(Float temp) {
        this.temp = temp - KELVIN_CORRECTION_VALUE;
    }

    public void setTemp_min(Float temp_min) {
        this.temp_min = temp_min - KELVIN_CORRECTION_VALUE;
    }

    public void setTemp_max(Float temp_max) {
        this.temp_max = temp_max - KELVIN_CORRECTION_VALUE;
    }
}
