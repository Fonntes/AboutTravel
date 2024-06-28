package com.example.abouttravel.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.abouttravel.data.entities.Local

@Dao
interface LocationDao {

    @Insert
    suspend fun insertLocation(local: Local)

    @Query("SELECT * FROM locations")
    fun getAllLocations(): LiveData<List<Local>>

    @Query("SELECT * FROM locations WHERE id = :id")
    fun getLocationById(id: Int): Local

    @Update
    suspend fun updateLocation(local: Local)

    @Delete
    suspend fun deleteLocation(local: Local)

    @Query("SELECT * FROM locations WHERE trip_id = :tripId")
    fun getLocationsForTrip(tripId: Int): LiveData<List<Local>>
}