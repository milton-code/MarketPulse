package com.example.marketpulse.ui.screens.authentication.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketpulse.domain.usecase.authentication.SignInUseCase
import com.example.marketpulse.domain.usecase.authentication.SignUpUseCase
import com.example.marketpulse.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class EmailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
): ViewModel() {
    val screenState: StateFlow<ScreenState> = savedStateHandle.getStateFlow("sign_up", true)
        .map { signUp ->
            if (signUp) {
                ScreenState.SignUp
            } else {
                ScreenState.SignIn
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ScreenState.SignUp
        )

    val userEmail = savedStateHandle.getStateFlow(key = "user_email", initialValue = "")
    val userPassword = savedStateHandle.getStateFlow(key = "user_password", initialValue = "")

    fun setUserEmail(email: String) {
        savedStateHandle["user_email"] = email
    }
    fun setUserPassword(password: String) {
        savedStateHandle["user_password"] = password
    }
    private val _primaryButtonState = MutableStateFlow<Resource<Unit>>(Resource.Idle)
    val primaryButtonState: StateFlow<Resource<Unit>> = _primaryButtonState

    fun onPrimaryButtonClick() {
        val email = userEmail.value
        val pass = userPassword.value
        val isSignUp = savedStateHandle.get<Boolean>("sign_up") ?: true

        viewModelScope.launch {
            if (isSignUp) {
                signUpUseCase(email, pass).collect { resource ->
                    _primaryButtonState.value = resource
                } // Llama al caso de uso de Registro
            } else {
                signInUseCase(email, pass).collect { resource ->
                    _primaryButtonState.value = resource
                }// Llama al caso de uso de Login
            }
        }
    }

    fun resetPrimaryButtonState() {
        _primaryButtonState.value = Resource.Idle
    }
}
//CHECK NOT NULL