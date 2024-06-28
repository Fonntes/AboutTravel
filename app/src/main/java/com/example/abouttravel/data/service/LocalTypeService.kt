package com.example.abouttravel.data.service

import com.example.abouttravel.data.entities.LocalType
import com.example.abouttravel.data.repository.LocalTypeRepository

class LocalTypeService(private val localTypeRepository: LocalTypeRepository) {

    val allLocalTypes = localTypeRepository.allLocalTypes

    suspend fun insert(localType: LocalType): Long {
        return localTypeRepository.insert(localType)
    }

    suspend fun update(localType: LocalType) {
        localTypeRepository.update(localType)
    }

    suspend fun delete(localType: LocalType) {
        localTypeRepository.delete(localType)
    }
}