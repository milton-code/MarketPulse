package com.example.marketpulse.data

import android.util.Log
import com.example.marketpulse.domain.repositoryGateway.DeviceRegRepository
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import jakarta.inject.Inject

@AndroidEntryPoint
class MarketPulseFirebaseService : FirebaseMessagingService() {

    @Inject
    lateinit var deviceRepository: DeviceRegRepository

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onNewToken(token: String) {
        // aquí recibes el FCM token cuando Firebase lo genera o renueva
        //lo guardarás en tu backend
        Log.d("FCM", "Token: $token")
        registerDevice(token)
    }

    private fun registerDevice(token: String) {
        FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val fid = task.result
                serviceScope.launch {
                    deviceRepository.registerDevice(fid, token).collect {
                        // Podrías manejar el estado si fuera necesario
                    }
                }
            } else {
                Log.e("FCM", "No se pudo obtener el FID", task.exception)
            }
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        // aquí recibes las notificaciones cuando la app está abierta
        Log.d("FCM", "Mensaje recibido: ${message.notification?.body}")
    }
}