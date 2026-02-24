package com.example.pitone.domain.model

data class Driver (
    val driverNumber: Int,
    val fullName: String,
    val firstName: String,
    val lastName: String,
    val teamName: String,
    val teamColour: String?,
    val nameAcronym: String,
    val headshotUrl: String?,
    val broadcastName: String
)
