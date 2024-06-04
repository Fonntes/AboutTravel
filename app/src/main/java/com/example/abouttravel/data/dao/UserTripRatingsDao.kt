package com.example.abouttravel.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.abouttravel.data.entities.UserTripRatings

@Dao
interface UserTripRatingsDao {
    @Insert
    suspend fun insert(userTripRatings: UserTripRatings)

    @Update
    suspend fun update(userTripRatings: UserTripRatings)

    @Delete
    suspend fun delete(userTripRatings: UserTripRatings)

    @Query("SELECT * FROM userTripRatings WHERE id = :id")
    suspend fun getRatingById(id: Int): UserTripRatings

    @Query("SELECT * FROM userTripRatings")
    fun getAllRatings(): LiveData<List<UserTripRatings>>
}