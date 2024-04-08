package com.example.abouttravel.data.repository

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.dao.LocationDao
import com.example.abouttravel.data.entities.Location

class LocationRepository(private val locationDao: LocationDao) {

    val allLocations: LiveData<List<Location>> = locationDao.getAllLocations()

    suspend fun insert(location: Location) {
        locationDao.insertLocation(location)
    }

    suspend fun update(location: Location) {
        locationDao.updateLocation(location)
    }

    suspend fun delete(location: Location) {
        locationDao.deleteLocation(location)
    }

    fun getLocationsForTrip(tripId: Int): List<Location> {
        return locationDao.getLocationsForTrip(tripId)
    }
}