package com.codecrafters.openweathermap.api;

import com.codecrafters.openweathermap.data.CurrentWeatherInfo;
import com.codecrafters.openweathermap.data.ForecastWeatherInfo;
import com.codecrafters.openweathermap.data.WeatherInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ingo on 30.03.2016.
 */
interface OpenWeatherMapService {

    @GET("weather?mode=json")
    Call<CurrentWeatherInfo> getCurrentWeather(@Query("apikey") String apiKey, @Query("q") String city);

    @GET("weather?mode=json")
    Call<CurrentWeatherInfo> getCurrentWeather(@Query("apikey") String apiKey, @Query("id") int city);

    @GET("weather?mode=json")
    Call<CurrentWeatherInfo> getCurrentWeather(@Query("apikey") String apiKey, @Query("lat") double latitude, @Query("lon") double longitude);


    @GET("forecast?mode=json")
    Call<ForecastWeatherInfo> getForecastWeather(@Query("apiKey") String apiKey, @Query("q") String city);

    @GET("forecast?mode=json")
    Call<ForecastWeatherInfo> getForecastWeather(@Query("apiKey") String apiKey, @Query("id") int city);

    @GET("forecast?mode=json")
    Call<ForecastWeatherInfo> getForecastWeather(@Query("apiKey") String apiKey, @Query("lat") double latitude, @Query("lon") double longitude);

}
