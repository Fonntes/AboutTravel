package com.example.abouttravel.data.service

import com.example.abouttravel.data.entities.UserLocalRatings
import com.example.abouttravel.data.repository.UserLocalRatingsRepository

class UserLocalRatingsService(private val userLocalRatingsRepository: UserLocalRatingsRepository) {

    val allRatings = userLocalRatingsRepository.ratings

    suspend fun insert(userLocalRatings: UserLocalRatings) {
        userLocalRatingsRepository.insert(userLocalRatings)
    }

    suspend fun update(userLocalRatings: UserLocalRatings) {
        userLocalRatingsRepository.update(userLocalRatings)
    }

    suspend fun delete(userLocalRatings: UserLocalRatings) {
        userLocalRatingsRepository.delete(userLocalRatings)
    }
}