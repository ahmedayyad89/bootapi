package com.project.mini.support;

import com.project.mini.model.WeatherModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class WeatherFactory {

    private static String weatherAPIName;

    @Value("${weather.apis.name}")
    public void setWeatherAPIName(String weatherAPIName) {
        this.weatherAPIName = weatherAPIName;
    }

    public static WeatherModel getWeather() {
        WeatherModel weatherModel = null;
        if(weatherAPIName.equals(Constant.OPEN_WEATHER_MAP)) {
            weatherModel = OpenWeatherMapAPI.getWeather();
        }
        return weatherModel;
    }
}
