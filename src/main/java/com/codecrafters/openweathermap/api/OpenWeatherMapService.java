package com.codecrafters.openweathermap.api;

import com.codecrafters.openweathermap.data.WeatherForecastInfo;
import com.codecrafters.openweathermap.data.WeatherInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ingo on 30.03.2016.
 */
interface OpenWeatherMapService {

    @GET("weather?mode=json")
    Call<WeatherInfo> getCurrentWeather(@Query("apikey") String apiKey, @Query("q") String city);

//    @GET("weather?q={city},{countryCode}&mode=json")
//    Call<WeatherInfo> getCurrentWeather(@Query("apikey") String apiKey, @Path("city") String city, @Path("countryCode") String countryCode);

    @GET("weather?mode=json")
    Call<WeatherInfo> getCurrentWeather(@Query("apikey") String apiKey, @Query("id") int city);

    @GET("weather?mode=json")
    Call<WeatherInfo> getCurrentWeather(@Query("apikey") String apiKey, @Query("lat") double latitude, @Query("lon") double longitude);


    @GET("forecast?mode=json")
    Call<WeatherForecastInfo> getForecastWeather(@Query("apiKey") String apiKey, @Query("q") String city);

//    @GET("forecast?q={city},{countryCode}&mode=json")
//    Call<WeatherForecastInfo> getForecastWeather(@Query("apiKey") String apiKey, @Path("city") String city, @Path("countryCode") String countryCode);

    @GET("forecast?mode=json")
    Call<WeatherForecastInfo> getForecastWeather(@Query("apiKey") String apiKey, @Query("id") int city);

    @GET("forecast?mode=json")
    Call<WeatherForecastInfo> getForecastWeather(@Query("apiKey") String apiKey, @Query("lat") double latitude, @Query("lon") double longitude);

}
