package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.Local
import com.example.abouttravel.data.repository.LocationRepository
import com.example.abouttravel.data.service.LocationService
import kotlinx.coroutines.launch

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val locationService: LocationService
    val allLocations: LiveData<List<Local>>

    init {
        val locationDao = AboutTravelDataBase.getDatabase(application).locationDao()
        val locationRepository = LocationRepository(locationDao)
        locationService = LocationService(locationRepository)
        allLocations = locationService.allLocations
    }

    fun insert(local: Local) = viewModelScope.launch {
        locationService.insert(local)
    }

    fun update(local: Local) = viewModelScope.launch {
        locationService.update(local)
    }

    fun delete(local: Local) = viewModelScope.launch {
        locationService.delete(local)
    }

    fun getLocationsForTrip(tripId: Int): List<Local> {
        return locationService.getLocationsForTrip(tripId)
    }
}