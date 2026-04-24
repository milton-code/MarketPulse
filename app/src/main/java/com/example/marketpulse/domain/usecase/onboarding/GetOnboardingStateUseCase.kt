package com.example.marketpulse.domain.usecase.onboarding

import com.example.marketpulse.domain.repositoryGateway.UserPreferencesRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetOnboardingStateUseCase @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) {
    operator fun invoke(): Flow<Boolean> = userPreferencesRepository.isOnboardingCompleted
}