package com.example.abouttravel.data.repository

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.dao.LocalTypeDao
import com.example.abouttravel.data.entities.LocalType

class LocalTypeRepository ( private val localTypeDao: LocalTypeDao): BaseRepository() {

    val allLocalTypes: LiveData<List<LocalType>> = localTypeDao.getAllLocalTypes()

    suspend fun insert(localType: LocalType) {
        localTypeDao.insertLocalType(localType)
    }

    suspend fun update(localType: LocalType) {
        localTypeDao.updateLocalType(localType)
    }

    suspend fun delete(localType: LocalType) {
        localTypeDao.deleteLocalType(localType)
    }
}