package com.example.abouttravel.data.repository

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.dao.UserTripSharedDao
import com.example.abouttravel.data.entities.UserTripShared

class UserTripSharedRepository(private val userTripSharedDao: UserTripSharedDao): BaseRepository() {

    val userTripShared: LiveData<List<UserTripShared>> = userTripSharedDao.getAllUsersTrips()

    suspend fun insert(userTripShared: UserTripShared) {
        userTripSharedDao.insert(userTripShared)
    }

    suspend fun update(userTripShared: UserTripShared) {
        userTripSharedDao.update(userTripShared)
    }

    suspend fun delete(userTripShared: UserTripShared) {
        userTripSharedDao.delete(userTripShared)
    }
}