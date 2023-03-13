package com.rezafarahani.climate.common

import kotlin.math.roundToInt

object Extension {

    //fun Double.toCelsius(): String = (((this - 32) * 5)/9).roundToInt().toString()
    fun Double.toCelsius(): String = (this - 273).roundToInt().toString()
}