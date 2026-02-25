package com.example.pitone.data.remote

import com.example.pitone.data.remote.dto.ChampionshipTeamDto
import com.example.pitone.data.remote.dto.DriverDto

interface OpenF1ApiService {

    suspend fun getDrivers(): List<DriverDto>

    suspend fun getTeams(): List<ChampionshipTeamDto>

}