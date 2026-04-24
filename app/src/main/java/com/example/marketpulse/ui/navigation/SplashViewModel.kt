package com.example.marketpulse.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketpulse.domain.usecase.onboarding.GetOnboardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(private val getOnboardingStateUseCase: GetOnboardingStateUseCase) : ViewModel() {
    private val _startDestination: MutableState<String?> = mutableStateOf(null)
    val startDestination: State<String?> = _startDestination

    init {
        viewModelScope.launch {
            getOnboardingStateUseCase().collect{ completed ->
                if (completed) {
                    _startDestination.value = "auth_graph"
                } else {
                    _startDestination.value = NavigationDestination.Onboarding.route
                }
            }
        }
    }
}
//SharingStarted.Eagerly hace que stateIn comience a colectar el flow
//al momento en que se crea el ViewModel, a diferencia de WhileSubscribed que
//necesitaba que alguien este suscrito al startDestination para que recien ahi stateIn
//empezara a colectar el flow.


