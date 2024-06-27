package com.example.abouttravel.data.service

import android.content.Context
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.repository.TripRepository
import kotlinx.coroutines.CoroutineScope

class TripService(private val tripRepository: TripRepository) {


    val allTrips = tripRepository.allTrips

    fun getTripById(id: Int): Trip?{
        return tripRepository.getTripById(id)
    }

    fun refreshTrips(viewModelScope: CoroutineScope) {
        tripRepository.refreshTrips(viewModelScope)
    }

    fun createTripApi(trip: Trip, viewModelScope: CoroutineScope){
        tripRepository.insertTripToApi(trip,viewModelScope)
    }

    fun updateTripApi(trip: Trip, viewModelScope: CoroutineScope){
        tripRepository.updateTripToApi(trip,viewModelScope)
    }

    fun deleteTripApi(id: Int, viewModelScope: CoroutineScope){
        tripRepository.deleteTripToApi(id,viewModelScope)
    }

    suspend fun insert(trip: Trip) {
        tripRepository.insert(trip)
    }

    suspend fun update(trip: Trip) {
        tripRepository.update(trip)
    }

    suspend fun delete(trip: Trip) {
        tripRepository.delete(trip)
    }


}
