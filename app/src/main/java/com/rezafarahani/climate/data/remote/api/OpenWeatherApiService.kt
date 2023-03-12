package com.rezafarahani.climate.data.remote.api

import com.rezafarahani.climate.data.remote.api.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {

    @GET("data/2.5/weather")
    suspend fun getTodaysWeatherReport(
        @Query("q") location: String,
        @Query("APPID") AppId: String = "561d07fc6a328199a735ecf271575032"
    ): WeatherDto
}