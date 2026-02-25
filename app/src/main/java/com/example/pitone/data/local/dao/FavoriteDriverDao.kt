package com.example.pitone.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pitone.data.local.entity.FavoriteDriverEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDriverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(entity: FavoriteDriverEntity)

    @Delete
    suspend fun deleteFavorite(entity: FavoriteDriverEntity)

    @Query("SELECT * FROM favorite_drivers ORDER BY fullName ASC")
    fun getAllFavorites(): Flow<List<FavoriteDriverEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_drivers WHERE driverNumber = :driverNumber)")
    suspend fun isFavorite(driverNumber: Int): Boolean

    @Query("SELECT * FROM favorite_drivers WHERE driverNumber = :driverNumber LIMIT 1")
    suspend fun getFavoriteByDriverNumber(driverNumber: Int): FavoriteDriverEntity?

}