package com.example.marketpulse.domain.usecase.notifications

import com.example.marketpulse.domain.repositoryGateway.PermissionRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetNotificationStatusUseCaseTest {

    private lateinit var getNotificationStatusUseCase: GetNotificationStatusUseCase
    private lateinit var fakePermissionRepository: FakePermissionRepository

    @Before
    fun setUp() {
        fakePermissionRepository = FakePermissionRepository()
        getNotificationStatusUseCase = GetNotificationStatusUseCase(fakePermissionRepository)
    }

    @Test
    fun `invoke returns true when permission is granted`() {
        fakePermissionRepository.setPermissionGranted(true)
        val result = getNotificationStatusUseCase()
        assertTrue(result)
    }

    @Test
    fun `invoke returns false when permission is not granted`() {
        fakePermissionRepository.setPermissionGranted(false)
        val result = getNotificationStatusUseCase()
        assertFalse(result)
    }

    private class FakePermissionRepository : PermissionRepository {
        private var isGranted = false

        fun setPermissionGranted(granted: Boolean) {
            isGranted = granted
        }

        override fun hasNotificationPermission(): Boolean {
            return isGranted
        }
    }
}
