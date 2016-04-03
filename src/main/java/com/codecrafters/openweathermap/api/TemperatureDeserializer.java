package com.codecrafters.openweathermap.api;

import com.codecrafters.openweathermap.data.Temperature;
import com.codecrafters.openweathermap.data.TemperatureUnit;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Created by Ingo on 30.03.2016.
 */
class TemperatureDeserializer extends JsonDeserializer<Temperature> {
    @Override
    public Temperature deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        return new Temperature(node.numberValue().doubleValue(), TemperatureUnit.KELVIN);
    }
}
