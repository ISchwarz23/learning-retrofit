package com.codecrafters.openweathermap.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Data-Object that represents kartesian coordinates.
 *
 * @author ISchwarz
 */
public final class Coordinates implements Serializable {

    @JsonProperty("lon")
    private double longitude;
    @JsonProperty("lat")
    private double latitude;

    private Coordinates() {
        // used should not be able to create instance
    }

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
