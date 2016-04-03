package com.codecrafters.openweathermap.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Data-Objects that holds weather forecast information.
 *
 * @author ISchwarz
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastInfo {

    @JsonProperty("city")
    private City city;
    @JsonProperty("list")
    private List<WeatherInfo> weatherInfos = new ArrayList<>();

    public City getCity() {
        return city;
    }

    public List<WeatherInfo> getWeatherInfos() {
        return weatherInfos;
    }

    @Override
    public String toString() {
        return "WeatherForecastInfo{" +
                "city=" + city +
                ", weatherInfos=" + weatherInfos +
                '}';
    }
}
