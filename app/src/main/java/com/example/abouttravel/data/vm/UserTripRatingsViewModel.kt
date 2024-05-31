package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.UserTripRatings
import com.example.abouttravel.data.repository.UserTripRatingsRepository
import com.example.abouttravel.data.service.UserTripRatingsService
import kotlinx.coroutines.launch

class UserTripRatingsViewModel(application: Application) : AndroidViewModel(application) {

    private val userTripRatingsService: UserTripRatingsService
    val userTripRatings: LiveData<List<UserTripRatings>>

    init {
        val userTripRatingsDao = AboutTravelDataBase.getDatabase(application).userTripRatingsDao()
        val userTripRatingsRepository = UserTripRatingsRepository(userTripRatingsDao)
        userTripRatingsService = UserTripRatingsService(userTripRatingsRepository)
        userTripRatings = userTripRatingsService.allRatings
    }

    fun insert(userTripRatings: UserTripRatings) = viewModelScope.launch {
        userTripRatingsService.insert(userTripRatings)
    }

    fun update(userTripRatings: UserTripRatings) = viewModelScope.launch {
        userTripRatingsService.update(userTripRatings)
    }

    fun delete(userTripRatings: UserTripRatings) = viewModelScope.launch {
        userTripRatingsService.delete(userTripRatings)
    }
}