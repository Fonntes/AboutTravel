package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.UserLocalRatings
import com.example.abouttravel.data.repository.UserLocalRatingsRepository
import com.example.abouttravel.data.service.UserLocalRatingsService
import kotlinx.coroutines.launch

class UserLocalRatingsViewModel(application: Application) : AndroidViewModel(application) {

    private val userLocalRatingsService: UserLocalRatingsService
    val userLocalRatings: LiveData<List<UserLocalRatings>>

    init {
        val userLocalRatingsDao = AboutTravelDataBase.getDatabase(application).userLocalRatingsDao()
        val userLocalRatingsRepository = UserLocalRatingsRepository(userLocalRatingsDao)
        userLocalRatingsService = UserLocalRatingsService(userLocalRatingsRepository)
        userLocalRatings = userLocalRatingsService.allRatings
    }

    fun insert(userLocalRatings: UserLocalRatings) = viewModelScope.launch {
        userLocalRatingsService.insert(userLocalRatings)
    }

    fun update(userLocalRatings: UserLocalRatings) = viewModelScope.launch {
        userLocalRatingsService.update(userLocalRatings)
    }

    fun delete(userLocalRatings: UserLocalRatings) = viewModelScope.launch {
        userLocalRatingsService.delete(userLocalRatings)
    }
}