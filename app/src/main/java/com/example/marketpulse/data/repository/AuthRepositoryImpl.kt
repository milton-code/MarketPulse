package com.example.marketpulse.data.repository

import com.example.marketpulse.domain.repositoryGateway.AuthRepository
import com.example.marketpulse.core.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import jakarta.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl @Inject constructor(val auth: FirebaseAuth): AuthRepository {
    /*override val currentUser: FirebaseUser?
        get() = auth.currentUser*/
    override val authState: Flow<FirebaseUser?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener {
            trySend(it.currentUser)
        }
        auth.addAuthStateListener(listener)
        awaitClose {
            auth.removeAuthStateListener(listener)
        }
    }

    override fun firebaseSignUp(email: String, password: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading) // Avisamos a la UI que empiece el Spinner
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error desconocido"))
        }
    }

    override fun firebaseSignIn(email: String, password: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading) // Avisamos a la UI que empiece el Spinner
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error desconocido"))
        }
    }

    override fun signOut() {
        auth.signOut()
    }
}