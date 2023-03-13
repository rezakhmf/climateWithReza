package com.rezafarahani.climate.ui.HomeScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezafarahani.climate.common.DEFAULT_CITY_NAME
import com.rezafarahani.climate.common.ResultData
import com.rezafarahani.climate.common.UiText
import com.rezafarahani.climate.domain.model.Weather
import com.rezafarahani.climate.domain.repository.LocalStorageRepository
import com.rezafarahani.climate.domain.usecase.weatherCity.GetTodaysWeatherReport
import com.rezafarahani.climate.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTodaysWeatherReportUseCase: GetTodaysWeatherReport,
    private val getLocalStorageRepositoryUseCase: LocalStorageRepository
) : ViewModel() {


    private var _todayWeatherUiState: MutableStateFlow<UIState<Weather>> =
        MutableStateFlow(UIState(isLoading = true))
    val todayWeatherUiState: StateFlow<UIState<Weather>> = _todayWeatherUiState.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UIState(isLoading = true)
        )

    init {
        weatherFor((DEFAULT_CITY_NAME))
    }

    private fun weatherFor(cityName: String) {
        viewModelScope.launch {
            getLocalStorageRepositoryUseCase.getCityName().collect {
                if (it.isNotEmpty()) {
                    fetchWeatherDetails(it).collect()
                } else {
                    fetchWeatherDetails(cityName).collect()
                }
            }
        }
    }

    private fun fetchWeatherDetails(cityName: String): Flow<Unit> =
        getTodaysWeatherReportUseCase(cityName)
            .map { result ->
                when (result) {
                    is ResultData.DoNothing -> {}
                    is ResultData.Loading -> _todayWeatherUiState.value =
                        UIState(isLoading = true)
                    is ResultData.Success -> _todayWeatherUiState.value =
                        UIState(data = result.data)
                    is ResultData.Failed -> _todayWeatherUiState.value =
                        UIState(error = UiText.DynamicText(result.message.toString()))
                }
            }

    fun searchCityClimate(cityName: String) {
        viewModelScope.launch {
            getLocalStorageRepositoryUseCase.saveCityName(cityName)
            weatherFor(cityName)
        }
    }
}
