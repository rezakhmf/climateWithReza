package com.rezafarahani.climate.di

import com.rezafarahani.climate.common.OPEN_WEATHER_BASE_URL
import com.rezafarahani.climate.data.remote.LoggingInterceptor.logBodyInterceptor
import com.rezafarahani.climate.data.remote.NetworkInterceptor.onlineInterceptor
import com.rezafarahani.climate.data.remote.api.OpenWeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logBodyInterceptor())
            .addNetworkInterceptor(onlineInterceptor())
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun getConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun getOpenWeatherApiService(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): OpenWeatherApiService {
        return Retrofit.Builder()
            .baseUrl(OPEN_WEATHER_BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
            .create(OpenWeatherApiService::class.java)
    }
}