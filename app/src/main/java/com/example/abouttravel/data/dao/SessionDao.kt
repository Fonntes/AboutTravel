package com.example.abouttravel.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.abouttravel.data.entities.Session

@Dao
interface SessionDao {

    @Insert
    suspend fun insertSession(session: Session): Long

    @Query("SELECT * FROM session LIMIT 1")
    fun getSession(): LiveData<Session>

    @Update
    suspend fun updateSession(session: Session)

    @Delete
    suspend fun deleteSession(session: Session)

    @Query("DELETE FROM session")
    suspend fun deleteAll()
}
