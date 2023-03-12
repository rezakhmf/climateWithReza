package com.rezafarahani.climate.domain.repository

import com.rezafarahani.climate.data.remote.api.dto.WeatherDto

interface WeatherRepository {
    suspend fun getTodaysWeatherReport(cityName: String): WeatherDto
}