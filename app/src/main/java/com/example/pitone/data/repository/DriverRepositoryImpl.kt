package com.example.pitone.data.repository

import androidx.room.Dao
import com.example.pitone.data.local.dao.FavoriteDriverDao
import com.example.pitone.data.local.entity.FavoriteDriverEntity
import com.example.pitone.data.mapper.toDomain
import com.example.pitone.data.remote.OpenF1ApiService
import com.example.pitone.domain.model.Driver
import com.example.pitone.domain.repository.DriverRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DriverRepositoryImpl @Inject constructor(
    private val api: OpenF1ApiService,
    private val favoriteDao: FavoriteDriverDao
): DriverRepository {

    private var driversCache: List<Driver> = emptyList()

    override suspend fun getDrivers(): Result<List<Driver>> {
        return runCatching { 
            val dtos = api.getDrivers()
            driversCache = dtos.map { it.toDomain() }
            driversCache
        }
    }

    override suspend fun getDriverDetails(driverNumber: Int): Driver? {
        if (driversCache.isEmpty()) {
            getDrivers().getOrNull() ?: return null
        }
        return driversCache.find { it.driverNumber == driverNumber }
    }

    override suspend fun toggleFavorite(driver: Driver) {
        val entity = FavoriteDriverEntity(
            id = driver.driverNumber,
            driverNumber = driver.driverNumber,
            fullName = driver.fullName,
            teamName = driver.teamName
        )
        val existing = favoriteDao.getFavoriteByDriverNumber(driver.driverNumber)

        if (existing != null) {
            favoriteDao.deleteFavorite(entity)
        } else {
            favoriteDao.insertFavorite(entity)
        }
     }

    override fun observeFavorites(): Flow<Set<Int>> {
        return favoriteDao.getAllFavorites().map { list ->
            list.map { it.driverNumber }.toSet()
        }
    }

}