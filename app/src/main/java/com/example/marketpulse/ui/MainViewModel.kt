package com.example.marketpulse.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketpulse.core.AppState
import com.example.marketpulse.domain.usecase.authentication.GetAuthStateUseCase
import com.example.marketpulse.domain.usecase.notifications.DeviceRegistrationUseCase
import com.example.marketpulse.domain.usecase.onboarding.GetOnboardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getOnboardingStateUseCase: GetOnboardingStateUseCase,
    private val getAuthStateUseCase: GetAuthStateUseCase,
    private val deviceRegistrationUseCase: DeviceRegistrationUseCase
) : ViewModel() {
    
    init {
        viewModelScope.launch(Dispatchers.IO){
            deviceRegistrationUseCase()
        }
    }
    val appState: StateFlow<AppState> = combine(
        getOnboardingStateUseCase(),
        getAuthStateUseCase()
    ) { onboardingCompleted, user ->
        when {
            !onboardingCompleted -> AppState.OnboardingRequired
            user != null -> AppState.Authenticated
            else -> AppState.Unauthenticated
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AppState.Loading
    )
}
