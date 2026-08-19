package com.example.marketpulse.domain.repositoryGateway

import com.example.marketpulse.core.Resource
import kotlinx.coroutines.flow.Flow

interface DeviceRegRepository {
    fun registerDevice(fid: String, fcmToken: String): Flow<Resource<Unit>>
}
