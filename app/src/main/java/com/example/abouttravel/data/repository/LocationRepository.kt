package com.example.abouttravel.data.repository

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.dao.LocationDao
import com.example.abouttravel.data.entities.Local

class LocationRepository(private val locationDao: LocationDao) {

    val allLocations: LiveData<List<Local>> = locationDao.getAllLocations()

    suspend fun insert(local: Local) {
        locationDao.insertLocation(local)
    }

    suspend fun update(local: Local) {
        locationDao.updateLocation(local)
    }

    suspend fun delete(local: Local) {
        locationDao.deleteLocation(local)
    }

    fun getLocationsForTrip(tripId: Int): LiveData<List<Local>> {
        return locationDao.getLocationsForTrip(tripId)
    }
}