package com.example.abouttravel.data.service

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.entities.Session
import com.example.abouttravel.data.repository.SessionRepository

class SessionService(private val sessionRepository: SessionRepository) {

    val session: LiveData<Session> = sessionRepository.session

    suspend fun insert(session: Session) {
        sessionRepository.insert(session)
    }

    suspend fun update(session: Session) {
        sessionRepository.update(session)
    }

    suspend fun delete(session: Session) {
        sessionRepository.delete(session)
    }

    suspend fun deleteAll() {
        sessionRepository.deleteAll()
    }
}
