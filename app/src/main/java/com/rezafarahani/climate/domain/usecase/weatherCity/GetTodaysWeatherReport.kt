package com.rezafarahani.climate.domain.usecase.weatherCity

import com.rezafarahani.climate.common.ResultData
import com.rezafarahani.climate.common.UiText
import com.rezafarahani.climate.common.errorResponse
import com.rezafarahani.climate.data.remote.api.dto.toWeather
import com.rezafarahani.climate.domain.model.Weather
import com.rezafarahani.climate.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTodaysWeatherReport @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(cityName: String): Flow<ResultData<Weather>> = flow {
        try {
            val weatherReport: Weather = repository.getTodaysWeatherReport(cityName).toWeather()
            emit(ResultData.Success(weatherReport))
        } catch (e: HttpException) {
            val message = errorResponse(e.code())
            emit(ResultData.Failed(message))
        } catch (e: IOException) {
            emit(ResultData.Failed(UiText.DynamicText(e.message.toString())))
        }
    }
}