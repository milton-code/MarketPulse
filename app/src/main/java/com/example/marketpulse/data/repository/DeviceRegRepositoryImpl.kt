package com.example.marketpulse.data.repository

import android.util.Log
import com.example.marketpulse.core.Resource
import com.example.marketpulse.data.remote.api.DeviceRegistrationApiService
import com.example.marketpulse.data.remote.dto.DeviceRegistrationDto
import com.example.marketpulse.domain.repositoryGateway.DeviceRegRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import jakarta.inject.Inject

class DeviceRegRepositoryImpl @Inject constructor(
    private val apiService: DeviceRegistrationApiService
) : DeviceRegRepository {

    override fun registerDevice(fid: String, fcmToken: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        try {
            Log.d("DeviceReg", "Request -> FID: $fid")
            Log.d("DeviceReg", "Request -> Token: ${fcmToken.take(30)}...")
            val dto = DeviceRegistrationDto(fid = fid, fcmToken = fcmToken)
            val response = apiService.registerDevice(dto)
            if (response.isSuccessful) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error("Error al registrar dispositivo: ${response.code()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error desconocido"))
        }
    }
}
