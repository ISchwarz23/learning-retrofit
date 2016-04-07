package com.codecrafters.openweathermap.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Ingo on 05.04.2016.
 */
public final class CurrentWeatherInfoDeserializer extends JsonDeserializer<CurrentWeatherInfo> {

    private ObjectMapper mapper;

    public CurrentWeatherInfoDeserializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public CurrentWeatherInfo deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        return deserialize(node);
    }

    private CurrentWeatherInfo deserialize(JsonNode node) throws JsonProcessingException {
        int id = node.get("id").numberValue().intValue();
        String name = node.get("name").textValue();
        String country = node.get("sys").get("country").textValue();
        Coordinates coordinates = mapper.treeToValue(node.get("coord"), Coordinates.class);
        int population = 0;

        WeatherInfo weatherInfo = mapper.treeToValue(node, WeatherInfo.class);
        return new CurrentWeatherInfo(new City(id, name, coordinates, country, population), weatherInfo);
    }

}
