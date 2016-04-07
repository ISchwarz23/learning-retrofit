package com.codecrafters.openweathermap.api;

import com.codecrafters.openweathermap.data.CurrentWeatherInfo;
import com.codecrafters.openweathermap.data.ForecastWeatherInfo;
import com.codecrafters.openweathermap.data.WeatherInfo;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests the correctness of {@link OpenWeatherMap}.
 *
 * @author ISchwarz
 */
public class OpenWeatherMapTest {

    private static final String CITY_NAME = "Friedrichshafen";
    private static OpenWeatherMap openWeatherMap;

    @BeforeClass
    public static void setUp() {
        openWeatherMap = new OpenWeatherMap("51e065dfd5df34b351f6c352f486e807");
    }

    @Test
    public void shouldGiveCurrentWeatherByCity() {
        CurrentWeatherInfo currentWeatherInfo = openWeatherMap.getCurrentWeather(CITY_NAME);
        assertEquals(CITY_NAME, currentWeatherInfo.getCity().getName());
        assertNotNull(currentWeatherInfo.getWeatherInfo().getTemperatureInfo());

        printWeatherInfo(currentWeatherInfo.getWeatherInfo());
    }

    @Test
    public void shouldGiveForecastWeatherByCity() {
        ForecastWeatherInfo forecastWeatherInfo = openWeatherMap.getForecastWeather(CITY_NAME);
        assertEquals(CITY_NAME, forecastWeatherInfo.getCity().getName());
        assertNotNull(forecastWeatherInfo.getWeatherInfos().get(0).getTemperatureInfo());

        forecastWeatherInfo.getWeatherInfos().forEach(OpenWeatherMapTest::printWeatherInfo);
    }

    private static void printWeatherInfo(WeatherInfo weatherInfo) {
        System.out.println(weatherInfo.getDateTime().toLocalDateTime() + " -> " + weatherInfo.getTemperatureInfo().getTemperature().inCelsius());
    }

}
