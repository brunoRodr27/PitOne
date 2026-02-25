package com.example.pitone.data.repository

import com.example.pitone.data.mapper.toDomain
import com.example.pitone.data.remote.OpenF1ApiService
import com.example.pitone.domain.model.Driver
import com.example.pitone.domain.model.Team
import com.example.pitone.domain.repository.DriverRepository
import com.example.pitone.domain.repository.TeamRepository
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val api: OpenF1ApiService,
    private val driverRepository: DriverRepository
): TeamRepository {

    private var teamsCache: List<Team> = emptyList()

    override suspend fun getTeams(): Result<List<Team>> {
        return runCatching {
            val driversResult = driverRepository.getDrivers()
            val drivers = driversResult.getOrElse { return Result.failure(it) }
            val teamDto = api.getTeams()

            teamsCache = if (teamDto.isEmpty()) {
                deriveTeamsFromDrivers(drivers)
            } else {
                teamDto.map { it.toDomain(drivers) }
            }
            teamsCache
        }
    }

    override suspend fun getTeamDetails(teamName: String): Team? {
        if (teamsCache.isEmpty()) {
            getTeams().getOrNull() ?: return null
        }
        return teamsCache.find { it.teamName == teamName }
    }

    private fun deriveTeamsFromDrivers(drivers: List<Driver>): List<Team> {
        return drivers
            .groupBy { it.teamName }
            .map { (teamName, teamDrivers) ->
                Team(
                    teamName = teamName,
                    position = null,
                    points = null,
                    teamColour = teamDrivers.firstOrNull()?.teamColour,
                    drivers = teamDrivers.map { it.fullName }
                )
            }
    }

}