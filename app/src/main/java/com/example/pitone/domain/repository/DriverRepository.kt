package com.example.pitone.domain.repository

import com.example.pitone.domain.model.Driver
import kotlinx.coroutines.flow.Flow

interface DriverRepository {

    suspend fun getDrivers(): Result<List<Driver>>

    suspend fun getDriverDetails(driverNumber: Int): Driver?

    suspend fun toggleFavorite(driver: Driver)

    fun observeFavorites(): Flow<Set<Int>>

}