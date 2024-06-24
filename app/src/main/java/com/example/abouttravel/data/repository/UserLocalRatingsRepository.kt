package com.example.abouttravel.data.repository

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.dao.UserLocalRatingsDao
import com.example.abouttravel.data.entities.UserLocalRatings

class UserLocalRatingsRepository(private val userLocalRatingsDao: UserLocalRatingsDao): BaseRepository() {

        val ratings: LiveData<List<UserLocalRatings>> = userLocalRatingsDao.getAllRatings()

        suspend fun insert(userLocalRatings: UserLocalRatings) {
            userLocalRatingsDao.insert(userLocalRatings)
        }

        suspend fun update(userLocalRatings: UserLocalRatings) {
            userLocalRatingsDao.update(userLocalRatings)
        }

        suspend fun delete(userLocalRatings: UserLocalRatings) {
            userLocalRatingsDao.delete(userLocalRatings)
        }
}