package com.example.abouttravel.data.service

import com.example.abouttravel.data.entities.UserTripShared
import com.example.abouttravel.data.repository.UserTripSharedRepository

class UserTripSharedService(private val userTripSharedRepository: UserTripSharedRepository) {

    val allUsersTrips = userTripSharedRepository.userTripShared

    suspend fun insert(userTripShared: UserTripShared) {
        userTripSharedRepository.insert(userTripShared)
    }

    suspend fun update(userTripShared: UserTripShared) {
        userTripSharedRepository.update(userTripShared)
    }

    suspend fun delete(userTripShared: UserTripShared) {
        userTripSharedRepository.delete(userTripShared)
    }
}