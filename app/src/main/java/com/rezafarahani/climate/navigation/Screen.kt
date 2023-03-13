package com.rezafarahani.climate.navigation


object ClimateWithRezaDestination  {
    const val HOME_SCREEN = "home_screen"
    const val CITY_NAME = "city_name"
}

sealed class Screen(val route: String) {
    object HomeScreen : Screen(ClimateWithRezaDestination.HOME_SCREEN)

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}