package com.example.marketpulse.data.remote.api

import com.example.marketpulse.data.remote.dto.DeviceRegistrationDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DeviceRegistrationApiService {
    @POST("/devices/register")
    suspend fun registerDevice(@Body registrationDto: DeviceRegistrationDto): Response<Unit>
}
