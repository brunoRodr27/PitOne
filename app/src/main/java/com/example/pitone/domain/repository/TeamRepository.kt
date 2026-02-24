package com.example.pitone.domain.repository

import com.example.pitone.domain.model.Team

interface TeamRepository {

    suspend fun getTeams(): Result<List<Team>>

    suspend fun getTeamDetails(teamName: String): Team?

}