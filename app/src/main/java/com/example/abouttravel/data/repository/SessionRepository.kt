package com.example.abouttravel.data.repository

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.dao.SessionDao
import com.example.abouttravel.data.entities.Session


class SessionRepository(private val sessionDao: SessionDao) {

        val Session = sessionDao.getsession()

        suspend fun insert(session: Session) {
            sessionDao.insertSession(session)
        }

        suspend fun update(session: Session) {
            sessionDao.updateSession(session)
        }

        suspend fun delete(session: Session) {
            sessionDao.deleteSession(session)
        }

}