package com.example.pitone.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverDto(
    @SerialName("driver_number") val driverNumber: Int,
    @SerialName("full_name") val fullName: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("team_name") val teamName: String,
    @SerialName("team_colour") val teamColour: String?,
    @SerialName("name_acronym") val nameAcronym: String,
    @SerialName("headshot_url") val headshotUrl: String? = null,
    @SerialName("broadcast_name") val broadcastName: String
)