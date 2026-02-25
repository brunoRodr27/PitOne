package com.example.pitone.data.mapper

import com.example.pitone.data.remote.dto.ChampionshipTeamDto
import com.example.pitone.domain.model.Driver
import com.example.pitone.domain.model.Team
import kotlin.collections.filter

fun ChampionshipTeamDto.toDomain(
    drivers: List<Driver>,
): Team {
    val teamDrivers = drivers
        .filter { it.teamName == teamName }
        .map { it.fullName }
    val teamColour = drivers.firstOrNull { it.teamName == teamName }?.teamColour

    return Team(
        teamName = teamName,
        position = positionCurrent,
        points = pointsCurrent,
        teamColour = teamColour,
        drivers = teamDrivers,
    )
}