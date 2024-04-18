package com.example.abouttravel.data.repository

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.dao.UsersTripsDao
import com.example.abouttravel.data.entities.UsersTrips

class UsersTripsRepository(private val userTripDao: UsersTripsDao) {

    val usersTrips: LiveData<List<UsersTrips>> = userTripDao.getAllUsersTrips()

    suspend fun insert(usersTrips: UsersTrips) {
        userTripDao.insert(usersTrips)
    }

    suspend fun update(usersTrips: UsersTrips) {
        userTripDao.update(usersTrips)
    }

    suspend fun delete(usersTrips: UsersTrips) {
        userTripDao.delete(usersTrips)
    }
}