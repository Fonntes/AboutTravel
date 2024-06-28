package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.Local
import com.example.abouttravel.data.repository.LocationRepository
import com.example.abouttravel.data.service.LocationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val locationService: LocationService
    private val locationRepository: LocationRepository

    init {
        val locationDao = AboutTravelDataBase.getDatabase(application).locationDao()
        locationRepository = LocationRepository(locationDao)
        locationService = LocationService(locationRepository)
    }

    fun insert(local: Local) {
        viewModelScope.launch(Dispatchers.IO) {
            locationService.insert(local)
        }
    }

    fun update(local: Local) {
        viewModelScope.launch(Dispatchers.IO) {
            locationService.update(local)
        }
    }

    fun delete(local: Local) {
        viewModelScope.launch(Dispatchers.IO) {
            locationService.delete(local)
        }
    }

    fun getLocationsForTrip(tripId: Int): LiveData<List<Local>> {
        return locationRepository.getLocationsForTrip(tripId)
    }
}
