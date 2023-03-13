package com.rezafarahani.climate.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rezafarahani.climate.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val DataStore_NAME = "CLIMATE_WITH_REZA"

val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = DataStore_NAME)


class LocalStorageRepositoryImp(private val context: Context) : LocalStorageRepository {

    companion object{
        val CITY_NAME = stringPreferencesKey("CITY_NAME")
    }

    override suspend fun saveCityName(citName: String) {
        context.datastore.edit { storage->
            storage[CITY_NAME] = citName
        }
    }

    override suspend fun getCityName() =
        context.datastore.data.map {
            it[CITY_NAME] ?: ""
        }
}