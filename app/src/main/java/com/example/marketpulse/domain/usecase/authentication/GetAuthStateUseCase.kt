package com.example.marketpulse.domain.usecase.authentication

import com.example.marketpulse.domain.repositoryGateway.AuthRepository
import com.google.firebase.auth.FirebaseUser
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAuthStateUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<FirebaseUser?> = repository.authState
}