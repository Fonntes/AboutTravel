package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.Session
import com.example.abouttravel.data.repository.SessionRepository
import com.example.abouttravel.data.service.SessionService
import kotlinx.coroutines.launch


class SessionViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionService: SessionService
    val Session: Session

    init {
        val sessionDao = AboutTravelDataBase.getDatabase(application).sessionDao()
        val sessionRepository = SessionRepository(sessionDao)
        sessionService = SessionService(sessionRepository)
        Session = sessionService.Session
    }

    fun insert(session: Session) = viewModelScope.launch {
        sessionService.insert(session)
    }

    fun update(session: Session) = viewModelScope.launch {
        sessionService.update(session)
    }

    fun delete(session: Session) = viewModelScope.launch {
        sessionService.delete(session)
    }
}