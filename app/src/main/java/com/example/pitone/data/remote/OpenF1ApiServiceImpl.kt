package com.example.pitone.data.remote

import com.example.pitone.data.remote.dto.ChampionshipTeamDto
import com.example.pitone.data.remote.dto.DriverDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.inject.Inject
import kotlin.collections.firstOrNull

class OpenF1ApiServiceImpl @Inject constructor(
    private val httpClient: HttpClient
): OpenF1ApiService {

    override suspend fun getDrivers(): List<DriverDto> {
        return httpClient.get("$BASE_URL/drivers?session_key=latest").body()
    }

    override suspend fun getTeams(): List<ChampionshipTeamDto> {
        val sessions = httpClient.get("$BASE_URL/sessions?session_key=latest").body<List<SessionDto>>()
        val sessionKey = sessions.firstOrNull()?.sessionKey ?: return emptyList()
        return httpClient.get("$BASE_URL/championship_teams?session_key=$sessionKey").body()
    }

    private companion object {
        private const val BASE_URL = "https://api.openf1.org/v1"
    }

}

@Serializable
private data class SessionDto (
    @SerialName("session_key") val sessionKey: Int
)