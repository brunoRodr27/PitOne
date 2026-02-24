package com.example.pitone.domain.model

data class Team (
    val teamName: String,
    val position: Int?,
    val points: Double?,
    val teamColour: String?,
    val drivers: List<String>
)
