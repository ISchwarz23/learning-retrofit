package com.codecrafters.openweathermap.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data-Object that represents kartesian coordinates.
 *
 * @author ISchwarz
 */
public class Coordinates {

    @JsonProperty("lon")
    private double longitude;
    @JsonProperty("lat")
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
