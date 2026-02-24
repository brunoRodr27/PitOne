package com.example.pitone.domain.usecase

import com.example.pitone.domain.model.Driver
import com.example.pitone.domain.repository.DriverRepository
import javax.inject.Inject

class GetDriversUseCase @Inject constructor(
    private val repository: DriverRepository
) {

    suspend operator fun invoke(): Result<List<Driver>> = repository.getDrivers()

}
