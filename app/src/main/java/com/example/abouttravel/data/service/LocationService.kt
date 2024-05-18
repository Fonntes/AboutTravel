package com.example.abouttravel.data.service

import com.example.abouttravel.data.entities.Local
import com.example.abouttravel.data.repository.LocationRepository

class LocationService(private val locationRepository: LocationRepository) {

    val allLocations = locationRepository.allLocations

    suspend fun insert(local: Local) {
        locationRepository.insert(local)
    }

    suspend fun update(local: Local) {
        locationRepository.update(local)
    }

    suspend fun delete(local: Local) {
        locationRepository.delete(local)
    }

    fun getLocationsForTrip(tripId: Int): List<Local> {
        return locationRepository.getLocationsForTrip(tripId)
    }
}