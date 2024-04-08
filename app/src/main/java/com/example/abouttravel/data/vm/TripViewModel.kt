package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.repository.TripRepository
import com.example.abouttravel.data.service.TripService
import kotlinx.coroutines.launch


class TripViewModel(application: Application) : AndroidViewModel(application) {

    private val tripService: TripService
    val allTrips: LiveData<List<Trip>>

    init {
        val tripDao = AboutTravelDataBase.getDatabase(application).tripDao()
        val tripRepository = TripRepository(tripDao)
        tripService = TripService(tripRepository)
        allTrips = tripService.allTrips
    }

    fun insert(trip: Trip) = viewModelScope.launch {
        tripService.insert(trip)
    }

    fun update(trip: Trip) = viewModelScope.launch {
        tripService.update(trip)
    }

    fun delete(trip: Trip) = viewModelScope.launch {
        tripService.delete(trip)
    }
}