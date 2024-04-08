package com.example.abouttravel.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.abouttravel.data.entities.LocalType

@Dao
interface LocalTypeDao {
    @Insert
     suspend fun insertLocalType(localType: LocalType): Long

    @Query("SELECT * FROM local_types")
    fun getAllLocalTypes():LiveData<List<LocalType>>

    @Query("SELECT * FROM local_types WHERE id = :id")
    fun getLocalTypeById(id: Int): LocalType

    @Update
    suspend fun updateLocalType(localType: LocalType)

    @Delete
    suspend fun deleteLocalType(localType: LocalType)
}