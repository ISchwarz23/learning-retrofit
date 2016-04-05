package com.codecrafters.openweathermap.api;

import com.codecrafters.openweathermap.data.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.time.ZonedDateTime;

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
        module.addDeserializer(CurrentWeatherInfo.class, new CurrentWeatherInfoDeserializer(mapper));
        module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        mapper.registerModule(module);

        openWeatherMapService = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/" + apiVersion + "/")
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build()
                .create(OpenWeatherMapService.class);
    }

    public CurrentWeatherInfo getCurrentWeather(String city) {
        try {
            Call<CurrentWeatherInfo> call = openWeatherMapService.getCurrentWeather(apiKey, city);
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getCurrentWeather(String city, SuccessCallback<CurrentWeatherInfo> callback) {
        getCurrentWeather(city, callback, null);
    }

    public void getCurrentWeather(String city, final SuccessCallback<CurrentWeatherInfo> successCallback, final FailCallback failCallback) {
        openWeatherMapService.getCurrentWeather(apiKey, city).enqueue(new Callback<CurrentWeatherInfo>() {
            @Override
            public void onResponse(final Call<CurrentWeatherInfo> call, final Response<CurrentWeatherInfo> response) {
                if (successCallback != null) {
                    successCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(final Call<CurrentWeatherInfo> call, final Throwable t) {
                if (failCallback != null) {
                    failCallback.onFail(t);
                }
            }
        });
    }

    public ForecastWeatherInfo getForecastWeather(String city) {
        try {
            Call<ForecastWeatherInfo> call = openWeatherMapService.getForecastWeather(apiKey, city);
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
