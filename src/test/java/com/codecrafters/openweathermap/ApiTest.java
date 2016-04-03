package com.codecrafters.openweathermap;

import com.codecrafters.openweathermap.api.OpenWeatherMap;
import com.codecrafters.openweathermap.data.WeatherForecastInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        openWeatherMap.getCurrentWeather("Friedrichshafen", ApiTest::assertNotNull, ApiTest::fail);
    }

    @Test
    public void shouldGiveForecastWeatherByCity() {
        WeatherForecastInfo weatherInfo = openWeatherMap.getForecastWeather("Friedrichshafen");
        assertNotNull(weatherInfo);
    }

    private static void assertNotNull(Object result) {
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    private static void fail(Throwable t) {
        System.out.println(t.getMessage());
        Assert.fail();
    }

}
