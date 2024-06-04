package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.UserTripShared
import com.example.abouttravel.data.repository.UserTripSharedRepository
import com.example.abouttravel.data.service.UserTripSharedService
import kotlinx.coroutines.launch

class UserTripSharedViewModel(application: Application) : AndroidViewModel(application) {

    private val userTripSharedService: UserTripSharedService
    val userTripShared: LiveData<List<UserTripShared>>

    init {
        val userTripSharedDao = AboutTravelDataBase.getDatabase(application).userTripSharedDao()
        val userTripSharedRepository = UserTripSharedRepository(userTripSharedDao)
        userTripSharedService = UserTripSharedService(userTripSharedRepository)
        userTripShared = userTripSharedService.allUsersTrips
    }

    fun insert(userTripShared: UserTripShared) = viewModelScope.launch {
        userTripSharedService.insert(userTripShared)
    }

    fun update(userTripShared: UserTripShared) = viewModelScope.launch {
        userTripSharedService.update(userTripShared)
    }

    fun delete(userTripShared: UserTripShared) = viewModelScope.launch {
        userTripSharedService.delete(userTripShared)
    }
}