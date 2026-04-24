package com.example.marketpulse.domain.usecase.authentication

import com.example.marketpulse.domain.repositoryGateway.AuthRepository
import com.example.marketpulse.utils.Resource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class SignUpUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Flow<Resource<Unit>> =
        repository.firebaseSignUp(
            email = email,
            password = password
        )

}
