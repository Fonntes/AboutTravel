package com.example.abouttravel.data.service

import com.example.abouttravel.data.entities.Location
import com.example.abouttravel.data.repository.LocationRepository

class LocationService(private val locationRepository: LocationRepository) {

    val allLocations = locationRepository.allLocations

    suspend fun insert(location: Location) {
        locationRepository.insert(location)
    }

    suspend fun update(location: Location) {
        locationRepository.update(location)
    }

    suspend fun delete(location: Location) {
        locationRepository.delete(location)
    }

    fun getLocationsForTrip(tripId: Int): List<Location> {
        return locationRepository.getLocationsForTrip(tripId)
    }
}