package com.rezafarahani.climate.weather

import com.google.common.truth.Truth.assertThat
import com.rezafarahani.climate.common.MainCoroutineRule
import com.rezafarahani.climate.data.remote.api.OpenWeatherApiService
import com.rezafarahani.climate.domain.model.Weather
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import  com.rezafarahani.climate.common.MockWebServerUtil.enqueueResponse
import com.rezafarahani.climate.data.remote.api.dto.toWeather

@OptIn(ExperimentalCoroutinesApi::class)
class OpenWeatherApiServiceTest {

    private val mockWebServer = MockWebServer()

    private lateinit var client: OkHttpClient

    private lateinit var openWeatherApiService: OpenWeatherApiService

    @get: Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        mockWebServer.start()

        client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()

        openWeatherApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getTodaysWeatherReport should fetch response successfully with 200 code`(): Unit =
        mainCoroutineRule.testScope.runTest {
            try {
                mockWebServer.enqueueResponse("weather_report.json", 200)
                val actualResponse =
                    openWeatherApiService.getTodaysWeatherReport("Chicago").toWeather()
                val expectedResponse = Weather(
                    cod = 200,
                    temp = 294.16,
                    humidity = 39,
                    wind = 2.36,
                    windAngle = 54,
                    name = "Chicago",
                    icon = "01n",
                )
                assertThat(actualResponse).isEqualTo(expectedResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
}