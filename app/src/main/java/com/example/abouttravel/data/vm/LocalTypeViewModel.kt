package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.LocalType
import com.example.abouttravel.data.repository.LocalTypeRepository
import com.example.abouttravel.data.service.LocalTypeService
import kotlinx.coroutines.launch

class LocalTypeViewModel(application: Application) : AndroidViewModel(application) {

    private val localTypeService: LocalTypeService
    val allLocalTypes: LiveData<List<LocalType>>

    init {
        val localTypeDao = AboutTravelDataBase.getDatabase(application).localTypeDao()
        val localTypeRepository = LocalTypeRepository(localTypeDao)
        localTypeService = LocalTypeService(localTypeRepository)
        allLocalTypes = localTypeService.allLocalTypes
    }

    fun insert(localType: LocalType) = viewModelScope.launch {
        localTypeService.insert(localType)
    }

    fun update(localType: LocalType) = viewModelScope.launch {
        localTypeService.update(localType)
    }

    fun delete(localType: LocalType) = viewModelScope.launch {
        localTypeService.delete(localType)
    }
}