package com.example.umbrella.model.datasource.remote

import com.example.umbrella.model.datasource.local.daysforecast.fivedayResponse
import com.example.umbrella.model.datasource.local.hourlyweather.MyWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather?")
    fun getWeatherData (@Query("zip") zip: String, @Query("APPID") app_id: String) : Call<MyWeather>

    @GET("/data/2.5/forecast?")
    fun getForecastData (@Query("zip") zip: String, @Query("APPID") app_id: String) : Call<fivedayResponse>
}