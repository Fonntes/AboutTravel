package com.example.abouttravel.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.abouttravel.data.entities.UsersTrips

@Dao
interface UsersTripsDao {
    @Insert
    suspend fun insert(usersTrips: UsersTrips)

    @Update
    suspend fun update(usersTrips: UsersTrips)

    @Delete
    suspend fun delete(usersTrips: UsersTrips)

    @Query("SELECT * FROM users_trips WHERE id = :id")
    suspend fun getUsersTripsById(id: Int): UsersTrips

    @Query("SELECT * FROM users_trips")
    fun getAllUsersTrips(): LiveData<List<UsersTrips>>
}