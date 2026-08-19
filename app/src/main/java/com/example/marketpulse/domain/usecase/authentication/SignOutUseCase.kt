package com.example.marketpulse.domain.usecase.authentication

import com.example.marketpulse.domain.repositoryGateway.AuthRepository
import jakarta.inject.Inject

class SignOutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() {
        repository.signOut()
    }
}