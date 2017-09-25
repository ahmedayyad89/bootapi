package com.project.mini.support;

import com.project.mini.model.WeatherContainerModel;
import com.project.mini.model.WeatherModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherMapAPI {
    private static String url;

    @Value("${weather.apis.url}")
    public void setURL(String url) {
        this.url = url;
    }

    public static WeatherModel getWeather() {
        System.out.println(url);
        RestTemplate restTemplate= new RestTemplate();
        WeatherContainerModel weatherContainerModel
                =  restTemplate.getForObject(url , WeatherContainerModel.class);
        return weatherContainerModel.getMain();
    }
}
