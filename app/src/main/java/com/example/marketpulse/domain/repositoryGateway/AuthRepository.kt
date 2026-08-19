package com.example.marketpulse.domain.repositoryGateway

import com.example.marketpulse.core.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    //val currentUser: FirebaseUser?
    val authState: Flow<FirebaseUser?>
    fun firebaseSignUp(email: String, password: String): Flow<Resource<Unit>>
    //suspend fun sendEmailVerification(): Resource<FirebaseUser>
    fun firebaseSignIn(email: String, password: String): Flow<Resource<Unit>>
    fun signOut()
}