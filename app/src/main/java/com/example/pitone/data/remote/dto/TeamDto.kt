package com.example.pitone.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionshipTeamDto (
    @SerialName("team_name") val teamName: String,
    @SerialName("position_current") val positionCurrent: Int? = null,
    @SerialName("points_current") val pointsCurrent: Double? = null
)