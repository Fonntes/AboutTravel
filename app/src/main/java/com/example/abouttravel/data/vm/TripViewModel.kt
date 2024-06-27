package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.api.ApiService
import com.example.abouttravel.api.TokenManager
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.repository.TripRepository
import com.example.abouttravel.data.service.TripService
import com.example.abouttravel.helpers.Network
import kotlinx.coroutines.launch


class TripViewModel(application: Application) : AndroidViewModel(application) {
    private val tripDao = AboutTravelDataBase.getDatabase(application).tripDao()
    private val tokenManager = TokenManager(application.applicationContext)
    private val apiService = ApiService(tokenManager)
    private val tripRepository = TripRepository(tripDao, apiService)
    private val tripService = TripService(tripRepository)
    private val network = Network(application.applicationContext)

    val allTrips: LiveData<List<Trip>> = tripService.allTrips

    fun refreshTrips() {
        if (network.isNetworkAvailable()) {
            viewModelScope.launch {
                tripService.refreshTrips(viewModelScope)
            }
        } else {
            error("No network available")
        }
    }

    fun createTripApi(trip: Trip){
        if(network.isNetworkAvailable()){
            viewModelScope.launch {
                tripService.createTripApi(trip,viewModelScope)
            }
        } else {
            error("No network available")
        }
    }

    fun updateTripApi(trip: Trip){
        if(network.isNetworkAvailable()){
            viewModelScope.launch {
                tripService.updateTripApi(trip,viewModelScope)
            }
        } else {
            error("No network available")
        }
    }

    fun deleteTripApi(id: Int){
        if(network.isNetworkAvailable()){
            viewModelScope.launch {
                tripService.deleteTripApi(id,viewModelScope)
            }
        } else {
            error("No network available")
        }
    }

    fun getTripById(id: Int): Trip?{
        return tripService.getTripById(id)
    }

    fun insert(trip: Trip) = viewModelScope.launch {
        tripRepository.insert(trip)
    }

    fun update(trip: Trip) = viewModelScope.launch {
        tripRepository.update(trip)
    }

    fun delete(trip: Trip) = viewModelScope.launch {
        tripRepository.delete(trip)
    }
}
