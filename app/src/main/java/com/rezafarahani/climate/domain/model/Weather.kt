package com.rezafarahani.climate.domain.model

data class Weather(
    val cod: Int = 0,
    val temp: Double? = 0.0,
    val wind: Double? = 0.0,
    val windAngle: Int? = 0,
    val humidity: Int? = 0,
    val name: String? = "",
    val icon: String? = "",
)