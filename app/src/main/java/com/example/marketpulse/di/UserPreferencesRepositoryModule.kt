package com.example.marketpulse.di

import com.example.marketpulse.data.repository.UserPreferencesRepositoryImpl
import com.example.marketpulse.domain.repositoryGateway.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserPreferencesRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(impl: UserPreferencesRepositoryImpl): UserPreferencesRepository
}