package com.codecrafters.openweathermap;

import static org.junit.Assert.*;

import com.codecrafters.openweathermap.data.TemperatureUnit;
import org.junit.Test;

/**
 * Created by Ingo on 30.03.2016.
 */
public class TemperatureUnitTest {

    private static final double TEMP_KELVIN = 283.15;
    private static final double TEMP_CELSIUS = 10;
    private static final double TEMP_FAHRENHEIT = 50;

    private static final double VALID_DELTA = 0.001;

    @Test
    public void testKelvinToKelvin() {
        assertEquals(TEMP_KELVIN, TemperatureUnit.KELVIN.toKelvin(TEMP_KELVIN), VALID_DELTA);
    }

    @Test
    public void testKelvinToCelsius() {
        assertEquals(TEMP_CELSIUS, TemperatureUnit.KELVIN.toCelsius(TEMP_KELVIN), VALID_DELTA);
    }

    @Test
    public void testKelvinToFahrenheit() {
        assertEquals(TEMP_FAHRENHEIT, TemperatureUnit.KELVIN.toFahreinheit(TEMP_KELVIN), VALID_DELTA);
    }

    @Test
    public void testCelsiusToKelvin() {
        assertEquals(TEMP_KELVIN, TemperatureUnit.CELSIUS.toKelvin(TEMP_CELSIUS), VALID_DELTA);
    }

    @Test
    public void testCelsiusToCelsius() {
        assertEquals(TEMP_CELSIUS, TemperatureUnit.CELSIUS.toCelsius(TEMP_CELSIUS), VALID_DELTA);
    }

    @Test
    public void testCelsiusToFahrenheit() {
        assertEquals(TEMP_FAHRENHEIT, TemperatureUnit.CELSIUS.toFahreinheit(TEMP_CELSIUS), VALID_DELTA);
    }

    @Test
    public void testFahrenheitToKelvin() {
        assertEquals(TEMP_KELVIN, TemperatureUnit.FAHRENHEIT.toKelvin(TEMP_FAHRENHEIT), VALID_DELTA);
    }

    @Test
    public void testFahrenheitToCelsius() {
        assertEquals(TEMP_CELSIUS, TemperatureUnit.FAHRENHEIT.toCelsius(TEMP_FAHRENHEIT), VALID_DELTA);
    }

    @Test
    public void testFahrenheitToFahrenheit() {
        assertEquals(TEMP_FAHRENHEIT, TemperatureUnit.FAHRENHEIT.toFahreinheit(TEMP_FAHRENHEIT), VALID_DELTA);
    }

}
