package com.example.abouttravel.helpers

import com.example.abouttravel.data.entities.Trip

data class ApiResponseCreateTrip(
    val status: String,
    val data: Trip?
)