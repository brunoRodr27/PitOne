package com.example.pitone.di

import com.example.pitone.data.repository.DriverRepositoryImpl
import com.example.pitone.data.repository.TeamRepositoryImpl
import com.example.pitone.domain.repository.DriverRepository
import com.example.pitone.domain.repository.TeamRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDriverRepository(impl: DriverRepositoryImpl): DriverRepository

    @Binds
    @Singleton
    abstract fun bindTeamRepository(impl: TeamRepositoryImpl): TeamRepository

}