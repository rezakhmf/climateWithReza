package com.rezafarahani.climate.common

import kotlin.math.roundToInt

object Extension {
    fun Double.toCelsius(): String = (this - 273).roundToInt().toString()
}