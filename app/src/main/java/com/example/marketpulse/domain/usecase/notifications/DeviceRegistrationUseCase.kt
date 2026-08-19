package com.example.marketpulse.domain.usecase.notifications

import android.util.Log
import com.example.marketpulse.core.Resource
import com.example.marketpulse.domain.repositoryGateway.DeviceRegRepository
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class DeviceRegistrationUseCase @Inject constructor(
    private val repository: DeviceRegRepository
) {
    suspend operator fun invoke() {
        try {
            val token = FirebaseMessaging.getInstance().token.await()
            val fid = FirebaseInstallations.getInstance().id.await()
            
            Log.d("SyncDevice", "Obtenido FID: $fid")
            Log.d("SyncDevice", "Obtenido Token: ${token.take(15)}...")

            repository.registerDevice(fid, token).collect { resource ->
                when (resource) {
                    is Resource.Success -> Log.d("SyncDevice", "Dispositivo registrado con éxito")
                    is Resource.Error -> Log.e("SyncDevice", "Error al registrar dispositivo: ${resource.message}")
                    else -> {}
                }
            }
        } catch (e: Exception) {
            Log.e("SyncDevice", "Excepción al sincronizar dispositivo", e)
        }
    }
}
