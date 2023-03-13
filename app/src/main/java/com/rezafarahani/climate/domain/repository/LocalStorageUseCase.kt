package com.rezafarahani.climate.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocalStorageRepository {

    suspend fun saveCityName(citName: String)
    suspend fun getCityName(): Flow<String>
}