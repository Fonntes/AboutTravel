package com.example.abouttravel.data.repository

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.dao.UserTripRatingsDao
import com.example.abouttravel.data.entities.UserTripRatings

class UserTripRatingsRepository(private val userTripRatingsDao: UserTripRatingsDao): BaseRepository() {

        val ratings: LiveData<List<UserTripRatings>> = userTripRatingsDao.getAllRatings()

        suspend fun insert(userTripRatings: UserTripRatings) {
            userTripRatingsDao.insert(userTripRatings)
        }

        suspend fun update(userTripRatings: UserTripRatings) {
            userTripRatingsDao.update(userTripRatings)
        }

        suspend fun delete(userTripRatings: UserTripRatings) {
            userTripRatingsDao.delete(userTripRatings)
        }
}