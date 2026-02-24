package com.example.pitone.domain.usecase

import com.example.pitone.domain.model.Team
import com.example.pitone.domain.repository.TeamRepository
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    private val repository: TeamRepository,
) {

    suspend operator fun invoke(): Result<List<Team>> = repository.getTeams()

}
