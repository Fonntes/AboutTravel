package com.example.abouttravel.data.service

import com.example.abouttravel.data.entities.Rating
import com.example.abouttravel.data.repository.RatingRepository

class RatingService(private val ratingRepository: RatingRepository) {

    val allRatings = ratingRepository.ratings

    suspend fun insert(rating: Rating) {
        ratingRepository.insert(rating)
    }

    suspend fun update(rating: Rating) {
        ratingRepository.update(rating)
    }

    suspend fun delete(rating: Rating) {
        ratingRepository.delete(rating)
    }
}