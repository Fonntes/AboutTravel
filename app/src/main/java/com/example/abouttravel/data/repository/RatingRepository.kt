package com.example.abouttravel.data.repository

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.dao.RatingDao
import com.example.abouttravel.data.entities.Rating

class RatingRepository(private val ratingDao: RatingDao) {

        val ratings: LiveData<List<Rating>> = ratingDao.getAllRatings()

        suspend fun insert(rating: Rating) {
            ratingDao.insert(rating)
        }

        suspend fun update(rating: Rating) {
            ratingDao.update(rating)
        }

        suspend fun delete(rating: Rating) {
            ratingDao.delete(rating)
        }
}