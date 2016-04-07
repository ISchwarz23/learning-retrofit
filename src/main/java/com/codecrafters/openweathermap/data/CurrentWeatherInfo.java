package com.codecrafters.openweathermap.data;

import java.io.Serializable;

/**
 * Created by Ingo on 05.04.2016.
 */
public final class CurrentWeatherInfo implements Serializable {

    private WeatherInfo weatherInfo;
    private City city;


    private CurrentWeatherInfo() {
        // used should not be able to create instance
    }

    CurrentWeatherInfo(City city, WeatherInfo weatherInfo) {
        this.city = city;
        this.weatherInfo = weatherInfo;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "CurrentWeatherInfo{" +
                "weatherInfo=" + weatherInfo +
                ", city=" + city +
                '}';
    }
}
