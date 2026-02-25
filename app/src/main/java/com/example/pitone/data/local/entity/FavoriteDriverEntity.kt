package com.example.pitone.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_drivers")
data class FavoriteDriverEntity (
    @PrimaryKey val id: Int,
    val driverNumber: Int,
    val fullName: String,
    val teamName: String
)