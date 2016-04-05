package com.codecrafters.openweathermap.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the implementation of {@link Temperature}.
 *
 * @author ISchwarz
 */
public class TemperatureTest {

    private static final double TEMP_KELVIN = 283.15;
    private static final double TEMP_CELSIUS = 10;
    private static final double TEMP_FAHRENHEIT = 50;

    private static final double VALID_DELTA = 0.001;

    @Test
    public void testKelvinTemperature() {
        assertCalculationMatches(new Temperature(TEMP_KELVIN, TemperatureUnit.KELVIN));
    }

    @Test
    public void testCelsiusTemperature() {
        assertCalculationMatches(new Temperature(TEMP_CELSIUS, TemperatureUnit.CELSIUS));
    }

    @Test
    public void testFahrenheitTemperature() {
        assertCalculationMatches(new Temperature(TEMP_FAHRENHEIT, TemperatureUnit.FAHRENHEIT));
    }

    private static void assertCalculationMatches(Temperature temperature) {
        assertEquals(TEMP_KELVIN, temperature.inKelvin(), VALID_DELTA);
        assertEquals(TEMP_CELSIUS, temperature.inCelsius(), VALID_DELTA);
        assertEquals(TEMP_FAHRENHEIT, temperature.inFahrenheit(), VALID_DELTA);
    }
}
