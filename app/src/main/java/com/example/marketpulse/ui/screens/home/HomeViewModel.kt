package com.example.marketpulse.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.marketpulse.domain.usecase.authentication.SignOutUseCase
import com.example.marketpulse.domain.usecase.notifications.GetNotificationStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getNotificationStatusUseCase: GetNotificationStatusUseCase
) : ViewModel() {

    fun checkNotificationPermission(): Boolean {
        return getNotificationStatusUseCase()
    }

    fun signOut() {
        signOutUseCase()
    }
}
