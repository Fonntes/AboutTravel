package com.example.abouttravel.data.repository

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.dao.TripDao
import com.example.abouttravel.data.entities.Trip


class TripRepository(private val tripDao: TripDao) {

    val allTrip: LiveData<List<Trip>> = tripDao.getAllTrips()

    suspend fun insert(trip: Trip) {
        tripDao.insertTrip(trip)
    }

    suspend fun update(trip: Trip) {
        tripDao.updateTrip(trip)
    }

    suspend fun delete(trip: Trip) {
        tripDao.deleteTrip(trip)
    }
}