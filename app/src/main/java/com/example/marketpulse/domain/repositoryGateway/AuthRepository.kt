package com.example.marketpulse.domain.repositoryGateway

import com.example.marketpulse.utils.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: FirebaseUser?//ojo
    suspend fun firebaseSignUp(email: String, password: String): Flow<Resource<Unit>>
    //suspend fun sendEmailVerification(): Resource<FirebaseUser>
    suspend fun firebaseSignIn(email: String, password: String): Flow<Resource<Unit>>
}