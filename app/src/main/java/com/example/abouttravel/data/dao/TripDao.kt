package com.example.abouttravel.data.dao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.abouttravel.data.entities.Trip

@Dao
interface TripDao {

    @Insert
    suspend fun insertTrip(trip: Trip): Long

    @Query("SELECT * FROM trips")
    fun getAllTrips(): LiveData<List<Trip>>

    @Query("SELECT * FROM trips WHERE id = :id")
    fun getTripById(id: Int): Trip?

    @Update
    suspend fun updateTrip(trip: Trip)

    @Delete
    suspend fun deleteTrip(trip: Trip)

    @Transaction
    suspend fun insertOrUpdateTrips(trips: List<Trip>) {
        trips.forEach { trip ->
            if (getTripById(trip.id) != null) {
                updateTrip(trip)
            } else {
                insertTrip(trip)
            }
        }
    }

    @Query("DELETE FROM trips")
    suspend fun deleteAllTrips()

}