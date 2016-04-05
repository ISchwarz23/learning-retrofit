package com.codecrafters.openweathermap;

import com.codecrafters.openweathermap.api.OpenWeatherMap;
import com.codecrafters.openweathermap.data.CurrentWeatherInfo;
import com.codecrafters.openweathermap.data.ForecastWeatherInfo;
import com.codecrafters.openweathermap.data.WeatherInfo;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        assertEquals("Friedrichshafen", currentWeatherInfo.getCity().getName());
        assertNotNull(currentWeatherInfo.getWeatherInfo().getTemperatureInfo());

        printWeatherInfo(currentWeatherInfo.getWeatherInfo());
    }

    @Test
    public void shouldGiveForecastWeatherByCity() {
        ForecastWeatherInfo forecastWeatherInfo = openWeatherMap.getForecastWeather("Friedrichshafen");
        assertEquals("Friedrichshafen", forecastWeatherInfo.getCity().getName());
        assertNotNull(forecastWeatherInfo.getWeatherInfos().get(0).getTemperatureInfo());

        forecastWeatherInfo.getWeatherInfos().forEach(ApiTest::printWeatherInfo);
    }

    private static void printWeatherInfo(WeatherInfo weatherInfo) {
        System.out.println(weatherInfo.getDateTime().toLocalDateTime() + " -> " + weatherInfo.getTemperatureInfo().getTemperature().inCelsius());
    }

}
