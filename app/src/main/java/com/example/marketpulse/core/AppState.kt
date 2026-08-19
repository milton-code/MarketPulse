package com.example.marketpulse.core

sealed class AppState {
    object Loading : AppState()
    object OnboardingRequired : AppState()
    object Authenticated : AppState()
    object Unauthenticated : AppState()
}
