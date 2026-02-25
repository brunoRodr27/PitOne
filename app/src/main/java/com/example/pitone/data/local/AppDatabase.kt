package com.example.pitone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pitone.data.local.dao.FavoriteDriverDao
import com.example.pitone.data.local.entity.FavoriteDriverEntity

@Database(
    entities = [FavoriteDriverEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {

    abstract fun favoriteDriverDao(): FavoriteDriverDao

}