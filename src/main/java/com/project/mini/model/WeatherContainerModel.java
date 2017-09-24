package com.project.mini.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherContainerModel {

   private WeatherModel main;

}
