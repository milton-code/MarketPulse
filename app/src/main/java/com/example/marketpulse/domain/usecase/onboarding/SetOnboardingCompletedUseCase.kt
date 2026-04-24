package com.example.marketpulse.domain.usecase.onboarding

import com.example.marketpulse.domain.repositoryGateway.UserPreferencesRepository
import jakarta.inject.Inject

class SetOnboardingCompletedUseCase@Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend operator fun invoke(completed: Boolean) = userPreferencesRepository.saveOnboardingState(completed)
}