package com.rezafarahani.climate.di

import android.content.Context
import com.rezafarahani.climate.data.local.LocalStorageRepositoryImp
import com.rezafarahani.climate.data.remote.api.OpenWeatherApiService
import com.rezafarahani.climate.data.repository.WeatherRepositoryImpl
import com.rezafarahani.climate.dispatcher.DispatcherProvider
import com.rezafarahani.climate.domain.repository.LocalStorageRepository
import com.rezafarahani.climate.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(
        openWeatherApiService: OpenWeatherApiService,
        dispatcherProvider: DispatcherProvider
    ): WeatherRepository =
        WeatherRepositoryImpl(openWeatherApiService, dispatcherProvider)


    @Singleton
    @Provides
    fun provideLocalStorageRepository(@ApplicationContext context: Context)
    : LocalStorageRepository =
        LocalStorageRepositoryImp(context = context)

}