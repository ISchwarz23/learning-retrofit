package com.codecrafters.openweathermap.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data-Object representing a city.
 *
 * @author ISchwarz
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("coord")
    private Coordinates coord;
    @JsonProperty("country")
    private String country;
    @JsonProperty("population")
    private int population;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coord=" + coord +
                ", country='" + country + '\'' +
                ", population=" + population +
                '}';
    }
}
