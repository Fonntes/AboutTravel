package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.UsersTrips
import com.example.abouttravel.data.repository.UsersTripsRepository
import com.example.abouttravel.data.service.UsersTripsService
import kotlinx.coroutines.launch

class UsersTripsViewModel(application: Application) : AndroidViewModel(application) {

    private val usersTripsService: UsersTripsService
    val usersTrips: LiveData<List<UsersTrips>>

    init {
        val usersTripsDao = AboutTravelDataBase.getDatabase(application).usersTripsDao()
        val usersTripsRepository = UsersTripsRepository(usersTripsDao)
        usersTripsService = UsersTripsService(usersTripsRepository)
        usersTrips = usersTripsService.allUsersTrips
    }

    fun insert(usersTrips: UsersTrips) = viewModelScope.launch {
        usersTripsService.insert(usersTrips)
    }

    fun update(usersTrips: UsersTrips) = viewModelScope.launch {
        usersTripsService.update(usersTrips)
    }

    fun delete(usersTrips: UsersTrips) = viewModelScope.launch {
        usersTripsService.delete(usersTrips)
    }
}