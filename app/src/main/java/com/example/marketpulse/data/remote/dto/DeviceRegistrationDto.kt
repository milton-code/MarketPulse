package com.example.marketpulse.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DeviceRegistrationDto(
    @SerializedName("installation_id")
    val fid: String,
    @SerializedName("fcm_token")
    val fcmToken: String
)
