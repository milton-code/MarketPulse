package com.example.marketpulse.domain.repositoryGateway

interface PermissionRepository {
    fun hasNotificationPermission(): Boolean
}
