package com.example.abouttravel.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.abouttravel.data.entities.UserTripShared

@Dao
interface UserTripSharedDao {
    @Insert
    suspend fun insert(userTripShared: UserTripShared)

    @Update
    suspend fun update(userTripShared: UserTripShared)

    @Delete
    suspend fun delete(userTripShared: UserTripShared)

    @Query("SELECT * FROM userTripShared WHERE id = :id")
    suspend fun getUsersTripsById(id: Int): UserTripShared

    @Query("SELECT * FROM userTripShared")
    fun getAllUsersTrips(): LiveData<List<UserTripShared>>
}