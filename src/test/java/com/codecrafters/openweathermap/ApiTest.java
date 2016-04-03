package com.codecrafters.openweathermap;

import com.codecrafters.openweathermap.api.OpenWeatherMap;
import com.codecrafters.openweathermap.data.WeatherForecastInfo;
import com.codecrafters.openweathermap.data.WeatherInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ingo on 30.03.2016.
 */
public class ApiTest {

    private OpenWeatherMap openWeatherMap;

    @Before
    public void setUp() {
        openWeatherMap = new OpenWeatherMap("51e065dfd5df34b351f6c352f486e807");
    }

    @Test
    public void shouldGiveCurrentWeatherByCity() {
        openWeatherMap.getCurrentWeather("Friedrichshafen",
                result -> {
                    System.out.println(result);
                    assertTrue(true);
                }, e -> {
                    e.printStackTrace();
                    assertTrue(false);
                });
    }

    @Test
    public void shouldGiveWeatherForecastByCity() {
        WeatherForecastInfo weatherInfo = openWeatherMap.getForecastWeather("Friedrichshafen");
        System.out.println(weatherInfo);
        assertTrue(weatherInfo != null);
    }

}
