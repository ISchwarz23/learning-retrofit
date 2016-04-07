package com.codecrafters.openweathermap.api;

import com.codecrafters.openweathermap.data.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sun.istack.internal.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * An API wrapper of the OpenWeatherMap.
 *
 * @author ISchwarz
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

    /**
     * Gives the current weather for the given city. This call blocks until the current weather is availible.
     *
     * @param city The city for which the current weather shall be returned. You can give a simple city name
     *             (e.g. "London") or city name + country code (e.g. "London,UK") to avoid confusions.
     * @return The current weather for the given city as {@link CurrentWeatherInfo} or {@code NULL} on error.
     */
    @Nullable
    public CurrentWeatherInfo getCurrentWeather(String city) {
        try {
            Call<CurrentWeatherInfo> call = openWeatherMapService.getCurrentWeather(apiKey, city);
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gives the current weather for the given city. This call is async and calls the callback on result.
     *
     * @param city     The city for which the current weather shall be returned. You can give a simple city name
     *                 (e.g. "London") or city name + country code (e.g. "London,UK") to avoid confusions.
     * @param callback The callback that is called when the current weather is available.
     */
    public void getCurrentWeather(String city, SuccessCallback<CurrentWeatherInfo> callback) {
        getCurrentWeather(city, callback, null);
    }

    /**
     * Gives the current weather for the given city. This call is async and calls the callback on result.
     *
     * @param city            The city for which the current weather shall be returned. You can give a simple city name
     *                        (e.g. "London") or city name + country code (e.g. "London,UK") to avoid confusions.
     * @param successCallback The callback that is called when the current weather is available.
     * @param failCallback    The callback that is called when an error occurs.
     */
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

    /**
     * Gives the weather forecast for the given city. This call blocks until the current weather is availible.
     *
     * @param city The city for which the weather forecast shall be returned. You can give a simple city name
     *             (e.g. "London") or city name + country code (e.g. "London,UK") to avoid confusions.
     * @return The weather forecast for the given city as {@link ForecastWeatherInfo} or {@code NULL} on error.
     */
    @Nullable
    public ForecastWeatherInfo getForecastWeather(String city) {
        try {
            Call<ForecastWeatherInfo> call = openWeatherMapService.getForecastWeather(apiKey, city);
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gives the weather forecast for the given city. This call is async and calls the callback on result.
     *
     * @param city     The city for which the weather forecast shall be returned. You can give a simple city name
     *                 (e.g. "London") or city name + country code (e.g. "London,UK") to avoid confusions.
     * @param callback The callback that is called when the weather forecast is available.
     */
    public void getForecastWeather(String city, final SuccessCallback<ForecastWeatherInfo> callback) {
        getForecastWeather(city, callback, null);
    }

    /**
     * Gives the weather forecast for the given city. This call is async and calls the callback on result.
     *
     * @param city            The city for which the weather forecast shall be returned. You can give a simple city name
     *                        (e.g. "London") or city name + country code (e.g. "London,UK") to avoid confusions.
     * @param successCallback The callback that is called when the weather forecast is available.
     * @param failCallback    The callback that is called when an error occurs.
     */
    public void getForecastWeather(String city, final SuccessCallback<ForecastWeatherInfo> successCallback, final FailCallback failCallback) {
        openWeatherMapService.getForecastWeather(apiKey, city).enqueue(new Callback<ForecastWeatherInfo>() {
            @Override
            public void onResponse(final Call<ForecastWeatherInfo> call, final Response<ForecastWeatherInfo> response) {
                if (successCallback != null) {
                    successCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(final Call<ForecastWeatherInfo> call, final Throwable t) {
                if (failCallback != null) {
                    failCallback.onFail(t);
                }
            }
        });
    }

    /**
     * Callback for service calls that ended successful.
     *
     * @param <T> The type of the received data.
     */
    public interface SuccessCallback<T> {

        /**
         * Callback method for successful service calls.
         *
         * @param result The result received from the service.
         */
        void onSuccess(T result);

    }

    /**
     * Callback for service calls that failed.
     */
    public interface FailCallback {

        /**
         * Callback method for failed service calls.
         *
         * @param e The throwable that occurred during service communication.
         */
        void onFail(Throwable e);

    }

}
