package com.example.marketpulse.di

import com.example.marketpulse.data.repository.DeviceRegRepositoryImpl
import com.example.marketpulse.domain.repositoryGateway.DeviceRegRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DeviceRegRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDeviceRegRepository(
        deviceRepositoryImpl: DeviceRegRepositoryImpl
    ): DeviceRegRepository
}
