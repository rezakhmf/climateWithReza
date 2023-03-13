package com.rezafarahani.climate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rezafarahani.climate.common.DEFAULT_CITY_NAME
import com.rezafarahani.climate.ui.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route + "/{${ClimateWithRezaDestination.CITY_NAME}}"
    ) {
        composable(
            route = Screen.HomeScreen.route + "/{${ClimateWithRezaDestination.CITY_NAME}}",
            arguments = listOf(navArgument(ClimateWithRezaDestination.CITY_NAME) {
                type = NavType.StringType
                defaultValue = DEFAULT_CITY_NAME
                nullable = true
            }
            )
        ) { backStackEntry ->
            HomeScreen(
                navController = navController,
            )
        }
    }
}

open class ClimateWithRezaNavigation(navHostController: NavHostController) {
    val navigationToCityWeather: (cityName: String) -> Unit = {
        navHostController.navigate(
            route = Screen.HomeScreen.route + "/" + it
        ) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}