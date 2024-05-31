package com.example.abouttravel.data.service

import com.example.abouttravel.data.entities.UserTripRatings
import com.example.abouttravel.data.repository.UserTripRatingsRepository

class UserTripRatingsService(private val userTripRatingsRepository: UserTripRatingsRepository) {

    val allRatings = userTripRatingsRepository.ratings

    suspend fun insert(userTripRatings: UserTripRatings) {
        userTripRatingsRepository.insert(userTripRatings)
    }

    suspend fun update(userTripRatings: UserTripRatings) {
        userTripRatingsRepository.update(userTripRatings)
    }

    suspend fun delete(userTripRatings: UserTripRatings) {
        userTripRatingsRepository.delete(userTripRatings)
    }
}