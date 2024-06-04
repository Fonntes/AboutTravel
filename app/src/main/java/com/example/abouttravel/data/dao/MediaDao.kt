package com.example.abouttravel.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.abouttravel.data.entities.Media

@Dao
interface MediaDao {
    @Insert
    suspend fun insertMedia(media: Media): Long

    @Query("SELECT * FROM media")
    fun getAllMedias(): LiveData<List<Media>>

    @Query("SELECT * FROM media WHERE id = :id")
    fun getMediaById(id: Int): Media

    @Update
    suspend fun updateMedia(media: Media)

    @Delete
    suspend fun deleteMedia(media: Media)
}