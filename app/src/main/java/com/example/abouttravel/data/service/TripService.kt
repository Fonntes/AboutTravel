package com.example.abouttravel.data.service

import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.repository.TripRepository

class TripService(private val tripRepository: TripRepository) {

    val allTrips = tripRepository.allTrip

    suspend fun insert(trip: Trip) {
        tripRepository.insert(trip)
    }

    suspend fun update(trip: Trip) {
        tripRepository.update(trip)
    }

    suspend fun delete(trip: Trip) {
        tripRepository.delete(trip)
    }
}