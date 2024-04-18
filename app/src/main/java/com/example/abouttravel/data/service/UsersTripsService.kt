package com.example.abouttravel.data.service

import com.example.abouttravel.data.entities.UsersTrips
import com.example.abouttravel.data.repository.UsersTripsRepository

class UsersTripsService(private val usersTripsRepository: UsersTripsRepository) {

    val allUsersTrips = usersTripsRepository.usersTrips

    suspend fun insert(usersTrips: UsersTrips) {
        usersTripsRepository.insert(usersTrips)
    }

    suspend fun update(usersTrips: UsersTrips) {
        usersTripsRepository.update(usersTrips)
    }

    suspend fun delete(usersTrips: UsersTrips) {
        usersTripsRepository.delete(usersTrips)
    }
}