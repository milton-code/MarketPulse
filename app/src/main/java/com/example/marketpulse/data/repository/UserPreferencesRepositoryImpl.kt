package com.example.marketpulse.data.repository

import android.util.Log
import com.example.marketpulse.domain.repositoryGateway.UserPreferencesRepository
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>): UserPreferencesRepository {

    private object Keys {
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        //val DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    override val isOnboardingCompleted: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map {//preferences->
            it[Keys.ONBOARDING_COMPLETED] ?: false
        }

    override suspend fun saveOnboardingState(completed: Boolean) {
        try{
            dataStore.edit {//preferences->
                it[Keys.ONBOARDING_COMPLETED] = completed
            }
        } catch (e: IOException) {
            Log.e("UserPreferencesRepository", "Error saving onboarding state",e)
        }
    }
}