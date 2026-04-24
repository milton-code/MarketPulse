package com.example.marketpulse.domain.repositoryGateway

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val isOnboardingCompleted: Flow<Boolean>
    //val isDarkMode: Flow<Boolean>

    suspend fun saveOnboardingState(completed: Boolean)
   // suspend fun saveThemeSetting(isDark: Boolean)
}


