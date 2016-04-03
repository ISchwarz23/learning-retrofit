package com.codecrafters.openweathermap.data;

/**
 * Created by Ingo on 30.03.2016.
 */
public class Temperature {

    private final double temperature;
    private final TemperatureUnit unit;

    public Temperature(final double temperature, final TemperatureUnit unit) {
        this.temperature = temperature;
        this.unit = unit;
    }

    public double inKelvin() {
        return unit.toKelvin(temperature);
    }

    public double inCelsius() {
        return unit.toCelsius(temperature);
    }

    public double inFahrenheit() {
        return unit.toFahreinheit(temperature);
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "temperature=" + temperature +
                ", unit=" + unit +
                '}';
    }
}
