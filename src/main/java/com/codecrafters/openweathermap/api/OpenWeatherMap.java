package com.codecrafters.openweathermap.api;

import com.codecrafters.openweathermap.data.Temperature;
import com.codecrafters.openweathermap.data.WeatherForecastInfo;
import com.codecrafters.openweathermap.data.WeatherInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

/**
 * Created by Ingo on 30.03.2016.
 */
public class OpenWeatherMap {

    private static final String DEFAULT_VERSION = "2.5";

    private final OpenWeatherMapService openWeatherMapService;
    private final String apiKey;

    public OpenWeatherMap(String apiKey) {
        this(apiKey, DEFAULT_VERSION);
    }

    public OpenWeatherMap(String apiKey, String apiVersion) {
        this.apiKey = apiKey;

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Temperature.class, new TemperatureDeserializer());
        mapper.registerModule(module);

        openWeatherMapService = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/" + apiVersion + "/")
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build()
                .create(OpenWeatherMapService.class);
    }

    public WeatherInfo getCurrentWeather(String city) {
        try {
            Call<WeatherInfo> call = openWeatherMapService.getCurrentWeather(apiKey, city);
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getCurrentWeather(String city, SuccessCallback<WeatherInfo> callback) {
        getCurrentWeather(city, callback, null);
    }

    public void getCurrentWeather(String city, final SuccessCallback<WeatherInfo> successCallback, final FailCallback failCallback) {
        openWeatherMapService.getCurrentWeather(apiKey, city).enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(final Call<WeatherInfo> call, final Response<WeatherInfo> response) {
                if (successCallback != null) {
                    successCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(final Call<WeatherInfo> call, final Throwable t) {
                if (failCallback != null) {
                    failCallback.onFail(t);
                }
            }
        });
    }

    public WeatherForecastInfo getForecastWeather(String city) {
        try {
            Call<WeatherForecastInfo> call = openWeatherMapService.getForecastWeather(apiKey, city);
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public interface SuccessCallback<T> {
        void onSuccess(T result);
    }

    public interface FailCallback {
        void onFail(Throwable e);
    }

}
