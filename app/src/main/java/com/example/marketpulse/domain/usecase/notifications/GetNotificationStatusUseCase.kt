package com.example.marketpulse.domain.usecase.notifications

import com.example.marketpulse.domain.repositoryGateway.PermissionRepository
import javax.inject.Inject

class GetNotificationStatusUseCase @Inject constructor(
    private val permissionRepository: PermissionRepository
) {
    operator fun invoke(): Boolean {
        return permissionRepository.hasNotificationPermission()
    }
}
