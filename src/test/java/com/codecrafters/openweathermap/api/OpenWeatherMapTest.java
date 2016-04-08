package com.codecrafters.openweathermap.api;

import com.codecrafters.openweathermap.data.CurrentWeatherInfo;
import com.codecrafters.openweathermap.data.ForecastWeatherInfo;
import com.codecrafters.openweathermap.data.WeatherInfo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the correctness of {@link OpenWeatherMap}.
 *
 * @author ISchwarz
 */
public class OpenWeatherMapTest {

    private static final int MILLIS_TO_WAIT_FOR_ASYNC_CALCULATIONS = 2500;
    private static final String CITY_NAME = "Friedrichshafen";

    private static OpenWeatherMap openWeatherMap;

    @BeforeClass
    public static void setUp() {
        openWeatherMap = new OpenWeatherMap("b1b15e88fa797225412429c1c50c122a");
    }

    @Test
    public void shouldGiveCurrentWeatherByCity() {
        CurrentWeatherInfo currentWeatherInfo = openWeatherMap.getCurrentWeather(CITY_NAME);
        assertEquals(CITY_NAME, currentWeatherInfo.getCity().getName());
        assertNotNull(currentWeatherInfo.getWeatherInfo().getTemperatureInfo());

        printWeatherInfo(currentWeatherInfo.getWeatherInfo());
    }

    @Test
    public void shouldGiveCurrentWeatherByCityAsync() {
        openWeatherMap.getCurrentWeather(CITY_NAME, OpenWeatherMapTest::assertNotNull, OpenWeatherMapTest::fail);
        waitForAsyncCalculation();
    }

    @Test
    public void shouldGiveForecastWeatherByCity() {
        ForecastWeatherInfo forecastWeatherInfo = openWeatherMap.getForecastWeather(CITY_NAME);
        assertEquals(CITY_NAME, forecastWeatherInfo.getCity().getName());
        assertNotNull(forecastWeatherInfo.getWeatherInfos().get(0).getTemperatureInfo());

        forecastWeatherInfo.getWeatherInfos().forEach(OpenWeatherMapTest::printWeatherInfo);
    }

    @Test
    public void shouldGiveForecastWeatherByCityAsync() {
        openWeatherMap.getForecastWeather(CITY_NAME, OpenWeatherMapTest::assertNotNull, OpenWeatherMapTest::fail);
        waitForAsyncCalculation();
    }

    private static void assertNotNull(Object object) {
        Assert.assertNotNull(object);
        System.out.println(object);
    }

    private static void fail(Throwable t) {
        t.printStackTrace();
        Assert.fail();
    }

    private static void printWeatherInfo(WeatherInfo weatherInfo) {
        System.out.println(weatherInfo.getDateTime().toLocalDateTime() + " -> " + weatherInfo.getTemperatureInfo().getTemperature().inCelsius());
    }

    private static void waitForAsyncCalculation() {
        try {
            Thread.sleep(MILLIS_TO_WAIT_FOR_ASYNC_CALCULATIONS);
        } catch (InterruptedException e) {
            // do nothing
        }
    }

}
