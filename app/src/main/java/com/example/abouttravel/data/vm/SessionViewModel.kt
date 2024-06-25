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
import java.util.Date

class SessionViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionService: SessionService
    val session: LiveData<Session>

    init {
        val sessionDao = AboutTravelDataBase.getDatabase(application).sessionDao()
        val sessionRepository = SessionRepository(sessionDao)
        sessionService = SessionService(sessionRepository)
        session = sessionService.session
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
    fun createTestSession() = viewModelScope.launch {
        val testSession = Session(
            id = 1, // ID fixo para teste
            name = "Test User",
            phoneNumber = "123456789",
            email = "test@example.com",
            profilePicture = "/path/to/profile/picture",
            description = "Test description",
            createdAt = Date(),
            updatedAt = Date(),
            deleteAt = Date()
        )
        sessionService.insert(testSession)
    }

    fun deleteAll() = viewModelScope.launch {
        sessionService.deleteAll()
    }
}
