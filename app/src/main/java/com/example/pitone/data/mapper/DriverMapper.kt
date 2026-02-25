package com.example.pitone.data.mapper

import com.example.pitone.data.remote.dto.DriverDto
import com.example.pitone.domain.model.Driver

fun DriverDto.toDomain(): Driver = Driver(
    driverNumber = driverNumber,
    fullName = fullName,
    firstName = firstName,
    lastName = lastName,
    teamName = teamName,
    teamColour = teamColour,
    nameAcronym = nameAcronym,
    headshotUrl = headshotUrl?.fixHeadshotUrl(),
    broadcastName = broadcastName
)

private fun String.fixHeadshotUrl(): String = replace(
    "d_driver_fallback_image.png/",
    "",
)