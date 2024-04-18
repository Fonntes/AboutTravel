package com.example.abouttravel.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.abouttravel.data.entities.Media
import com.example.abouttravel.data.entities.Rating

@Dao
interface RatingDao {
    @Insert
    suspend fun insert(rating: Rating)

    @Update
    suspend fun update(rating: Rating)

    @Delete
    suspend fun delete(rating: Rating)

    @Query("SELECT * FROM ratings WHERE id = :id")
    suspend fun getRatingById(id: Int): Rating

    @Query("SELECT * FROM ratings")
    fun getAllRatings(): LiveData<List<Rating>>
}