package com.example.abouttravel.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.abouttravel.data.entities.UserLocalRatings

@Dao
interface UserLocalRatingsDao {
    @Insert
    suspend fun insert(userLocalRatings: UserLocalRatings)

    @Update
    suspend fun update(userLocalRatings: UserLocalRatings)

    @Delete
    suspend fun delete(userLocalRatings: UserLocalRatings)

    @Query("SELECT * FROM userLocalRatings WHERE id = :id")
    suspend fun getRatingById(id: Int): UserLocalRatings

    @Query("SELECT * FROM userLocalRatings")
    fun getAllRatings(): LiveData<List<UserLocalRatings>>
}