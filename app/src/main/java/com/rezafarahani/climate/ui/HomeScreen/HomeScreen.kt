package com.rezafarahani.climate.ui.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.rezafarahani.climate.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rezafarahani.climate.common.Extension.toCelsius
import com.rezafarahani.climate.domain.model.Weather
import com.rezafarahani.climate.ui.UIState
import com.rezafarahani.climate.ui.component.ShowError
import com.rezafarahani.climate.ui.component.ShowLoading
import com.rezafarahani.climate.ui.theme.BackgroundGrey
import com.rezafarahani.climate.ui.theme.TextWhite

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val uiState: UIState<Weather> by viewModel.todayWeatherUiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        modifier = modifier,
        navController = navController,
        uiState = uiState,
        searchCityButtonPressed = viewModel::searchCityClimate
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    uiState: UIState<Weather>,
    searchCityButtonPressed: (String) -> Unit
) {

    var selectedCity by remember { mutableStateOf("") }

    // Screen Loading state
    if (uiState.isLoading)
        ShowLoading(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGrey)
        )
    // Screen Error state
    else if (
        !uiState.error?.asString(LocalContext.current).isNullOrEmpty()
    ) {
        ShowError(
            error = uiState.error?.asString(LocalContext.current),
            modifier = modifier
                .fillMaxSize()
                .background(BackgroundGrey)
        )
    }
    // Screen Data state
    else {

        val weather = uiState.data

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            weather?.name?.let {
                Text(
                    modifier = modifier.padding(top = 10.dp),
                    text = it,
                    style = MaterialTheme.typography.headlineLarge,
                    color = TextWhite
                )
            }

            Text(
                modifier = modifier.padding(top = 10.dp),
                text = stringResource(
                    id = R.string.celsius,
                    weather?.temp?.toCelsius() ?: stringResource(id = R.string.not_available)
                ),
                style = MaterialTheme.typography.headlineLarge,
                color = TextWhite
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_humidity),
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.location_icon_content)
                )
                Text(
                    modifier = modifier.padding(start = 5.dp),
                    text = stringResource(id = R.string.percent, weather?.humidity.toString()),
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextWhite
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier.padding(start = 2.dp),
                    painter = painterResource(id = R.drawable.ic_wind),
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.location_icon_content)
                )
                Text(
                    modifier = modifier.padding(start = 5.dp),
                    text = stringResource(id = R.string.speed, weather?.wind.toString()),
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextWhite
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = selectedCity,
                    label = { Text(text = "Enter Your Name") },
                    onValueChange = {
                        selectedCity = it
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = colorResource(R.color.white),
                        focusedLabelColor = colorResource(R.color.white),
                        unfocusedLabelColor = colorResource(R.color.white),
                        focusedBorderColor = colorResource(R.color.teal_200),
                        unfocusedBorderColor = colorResource(R.color.white)
                    )
                )
            }
            Spacer(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            Button(onClick = {
                if (selectedCity.isNotEmpty())
                    searchCityButtonPressed(selectedCity)
            }) {
                Text(text = "Search")
            }
        }
    }
}