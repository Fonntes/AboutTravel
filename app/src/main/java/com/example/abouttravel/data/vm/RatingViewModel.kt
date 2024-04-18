package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.Rating
import com.example.abouttravel.data.repository.RatingRepository
import com.example.abouttravel.data.service.RatingService
import kotlinx.coroutines.launch

class RatingViewModel(application: Application) : AndroidViewModel(application) {

    private val ratingService: RatingService
    val ratings: LiveData<List<Rating>>

    init {
        val ratingDao = AboutTravelDataBase.getDatabase(application).ratingDao()
        val ratingRepository = RatingRepository(ratingDao)
        ratingService = RatingService(ratingRepository)
        ratings = ratingService.allRatings
    }

    fun insert(rating: Rating) = viewModelScope.launch {
        ratingService.insert(rating)
    }

    fun update(rating: Rating) = viewModelScope.launch {
        ratingService.update(rating)
    }

    fun delete(rating: Rating) = viewModelScope.launch {
        ratingService.delete(rating)
    }
}