package com.rezafarahani.climate.di

import com.rezafarahani.climate.dispatcher.AppDispatcher
import com.rezafarahani.climate.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = AppDispatcher()
}