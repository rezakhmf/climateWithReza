package com.rezafarahani.climate.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.rezafarahani.climate.common.MainCoroutineRule
import com.rezafarahani.climate.common.ResultData
import com.rezafarahani.climate.domain.repository.LocalStorageRepository
import com.rezafarahani.climate.domain.repository.WeatherRepository
import com.rezafarahani.climate.domain.usecase.weatherCity.GetTodaysWeatherReport
import com.rezafarahani.climate.ui.HomeScreen.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers

class HomeViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val mainCoroutineRule = MainCoroutineRule()

    @RelaxedMockK
    private lateinit var getTodayWeatherUseCase: GetTodaysWeatherReport

    @RelaxedMockK
    private lateinit var getLocalStorageRepositoryUseCase: LocalStorageRepository



    private lateinit var viewModel: HomeViewModel

    /**
     * this method is helps to initiate mockk and setup mocked objects before tests are run
     */
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic(WeatherRepository::class)
        viewModel = HomeViewModel(
            getTodayWeatherUseCase,
            getLocalStorageRepositoryUseCase
        )
    }

    /**
     * this method runs at the end of tests to unmockk all mocked objects
     */
    @After
    fun teardown() {
        unmockkAll()
    }

    /**
     * testing main method to fetch weather details.
     * this tests that the use cases are working as expected and returning series of flows
     * contains loading, success and failure states
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchWeatherDetails, calls uses cases and fetch flow data successfully`() =
        mainCoroutineRule.testScope.runTest {
            coEvery { getTodayWeatherUseCase(ArgumentMatchers.anyString()) } returns
                    flowOf(ResultData.Loading, ResultData.Success(null), ResultData.Failed(null))

            viewModel.searchCityClimate(ArgumentMatchers.anyString())

            getTodayWeatherUseCase.invoke(ArgumentMatchers.anyString()).test {
                assertEquals(ResultData.Loading, awaitItem())
                assertEquals(ResultData.Success(null), awaitItem())
                assertEquals(ResultData.Failed(null), awaitItem())
                awaitComplete()
            }

        }
}