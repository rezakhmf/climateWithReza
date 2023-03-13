package com.rezafarahani.climate.data.repository

import com.rezafarahani.climate.data.remote.api.OpenWeatherApiService
import com.rezafarahani.climate.data.remote.api.dto.WeatherDto
import com.rezafarahani.climate.dispatcher.DispatcherProvider
import com.rezafarahani.climate.domain.repository.WeatherRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val openWeatherApiService: OpenWeatherApiService,
    private val dispatcher: DispatcherProvider,
) : WeatherRepository {

    override suspend fun getTodaysWeatherReport(cityName: String): WeatherDto =
        withContext(dispatcher.io) { openWeatherApiService.getTodaysWeatherReport(location = cityName) }
}


