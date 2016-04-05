package com.codecrafters.openweathermap;

import com.codecrafters.openweathermap.api.OpenWeatherMap;
import com.codecrafters.openweathermap.data.CurrentWeatherInfo;
import com.codecrafters.openweathermap.data.ForecastWeatherInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Ingo on 30.03.2016.
 */
public class ApiTest {

    private static OpenWeatherMap openWeatherMap;

    @BeforeClass
    public static void setUp() {
        openWeatherMap = new OpenWeatherMap("51e065dfd5df34b351f6c352f486e807");
    }

    @Test
    public void shouldGiveCurrentWeatherByCity() {
        CurrentWeatherInfo currentWeatherInfo = openWeatherMap.getCurrentWeather("Friedrichshafen");
        Assert.assertEquals("Friedrichshafen", currentWeatherInfo.getCity().getName());
        Assert.assertNotNull(currentWeatherInfo.getWeatherInfo().getTemperatureInfo());
    }

    @Test
    public void shouldGiveForecastWeatherByCity() {
        ForecastWeatherInfo forecastWeatherInfo = openWeatherMap.getForecastWeather("Friedrichshafen");
        Assert.assertEquals("Friedrichshafen", forecastWeatherInfo.getCity().getName());
        Assert.assertNotNull(forecastWeatherInfo.getWeatherInfos().get(0).getTemperatureInfo());
    }

}
