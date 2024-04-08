package com.example.abouttravel.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.abouttravel.data.entities.Location

@Dao
interface LocationDao {

    @Insert
    suspend fun insertLocation(location: Location): Long

    @Query("SELECT * FROM locations")
    fun getAllLocations(): LiveData<List<Location>>

    @Query("SELECT * FROM locations WHERE id = :id")
    fun getLocationById(id: Int): Location

    @Update
    suspend fun updateLocation(location: Location)

    @Delete
    suspend fun deleteLocation(location: Location)

    @Query("SELECT * FROM locations WHERE trip_id = :tripId")
    fun getLocationsForTrip(tripId: Int): List<Location>
}