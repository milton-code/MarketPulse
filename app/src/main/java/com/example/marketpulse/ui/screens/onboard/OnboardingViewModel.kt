package com.example.marketpulse.ui.screens.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketpulse.domain.usecase.onboarding.SetOnboardingCompletedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val setOnboardingCompletedUseCase: SetOnboardingCompletedUseCase): ViewModel() {
    fun saveOnboardingState(completed: Boolean) {
        viewModelScope.launch {
            setOnboardingCompletedUseCase(completed)
        }
    }
}