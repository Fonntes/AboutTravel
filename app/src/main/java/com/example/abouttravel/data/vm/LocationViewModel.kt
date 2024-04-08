package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.Location
import com.example.abouttravel.data.repository.LocationRepository
import com.example.abouttravel.data.service.LocationService
import kotlinx.coroutines.launch

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val locationService: LocationService
    val allLocations: LiveData<List<Location>>

    init {
        val locationDao = AboutTravelDataBase.getDatabase(application).locationDao()
        val locationRepository = LocationRepository(locationDao)
        locationService = LocationService(locationRepository)
        allLocations = locationService.allLocations
    }

    fun insert(location: Location) = viewModelScope.launch {
        locationService.insert(location)
    }

    fun update(location: Location) = viewModelScope.launch {
        locationService.update(location)
    }

    fun delete(location: Location) = viewModelScope.launch {
        locationService.delete(location)
    }

    fun getLocationsForTrip(tripId: Int): List<Location> {
        return locationService.getLocationsForTrip(tripId)
    }
}