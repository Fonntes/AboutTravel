package com.example.abouttravel.data.repository

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import com.example.abouttravel.api.ApiService
import com.example.abouttravel.api.TokenManager
import com.example.abouttravel.data.dao.TripDao
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.service.TripService
import com.example.abouttravel.helpers.ApiResponseCreateTrip
import com.google.gson.Gson
import com.google.gson.JsonParseException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class TripRepository(
    private val tripDao: TripDao,
    private val apiService: ApiService
) {

    val allTrips: LiveData<List<Trip>> = tripDao.getAllTrips()

    fun getTripById(id: Int): Trip? {
         return tripDao.getTripById(id)
    }

    fun refreshTrips(viewModelScope: CoroutineScope) {
        Log.d("TripRepository", "Refreshing trips...")
        apiService.getTrips().enqueue(object : Callback<List<Trip>> { // Change to List<Trip>
            override fun onResponse(call: Call<List<Trip>>, response: Response<List<Trip>>) {
                if (response.isSuccessful) {
                    val trips = response.body() ?: emptyList()
                    viewModelScope.launch(Dispatchers.IO) {
                        //Log.d("TripRepository", "Deleting all trips from database...")
                        tripDao.deleteAllTrips()
                        tripDao.insertOrUpdateTrips(trips)
                    }
                } else {
                    val errorBody = response.errorBody()
                    Log.e("TripRepository", "API request failed with code ${response.code()}: $errorBody")
                }
            }

            override fun onFailure(call: Call<List<Trip>>, t: Throwable) {
                Log.e("TripRepository", "API request failed: ${t.message}", t)
            }
        })
    }

    suspend fun insert(trip: Trip) {
        tripDao.insertTrip(trip)
    }

    fun insertTripToApi(trip: Trip, viewModelScope: CoroutineScope) {
        Log.d("TripRepository", "Sending trip to API: $trip")
        apiService.createTrip(trip).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    viewModelScope.launch(Dispatchers.IO) {
                        Log.d("TripRepository", "Inserting trips in database...")
                        tripDao.insertTrip(trip)
                    }
                } else {

                    val errorBody = response.errorBody()?.string()
                    Log.e("TripRepository", "API request failed with code: ${response.code()}")
                    Log.e("TripRepository", "Error response: $errorBody")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("TripRepository", "API request failed: ${t.message}", t)

                if (t is IOException) {
                    Log.e("TripRepository", "Network error: ${t.message}", t)
                } else if (t is HttpException) {
                    Log.e("TripRepository", "HTTP error: ${t.code()} - ${t.message()}", t)
                }
            }
        })
    }

    fun updateTripToApi(trip: Trip, viewModelScope: CoroutineScope) {
        Log.d("TripRepository", "Sending trip to API with ID: ${trip.id}, Trip data: $trip")
        apiService.updateTrip(trip).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    Log.d("TripRepository", "API response: ${response.code()} - $responseBody")
                }else
                {
                    val errorBody = response.errorBody()?.string()
                    Log.e("TripRepository", "API request failed with code: ${response.code()}")
                    Log.e("TripRepository", "Error response: $errorBody")
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("TripRepository", "API request failed: ${t.message}", t)
            }
        })
    }

    fun deleteTripToApi(id: Int, viewModelScope: CoroutineScope) {
        Log.d("TripRepository", "Deleting trip with ID: $id")
        apiService.deleteTrip(id).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    Log.d("TripRepository", "API response: ${response.code()} - $responseBody")
                }else
                {
                    val errorBody = response.errorBody()?.string()
                    Log.e("TripRepository", "API request failed with code: ${response.code()}")
                    Log.e("TripRepository", "Error response: $errorBody")
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("TripRepository", "API request failed: ${t.message}", t)
            }
        })
    }

    suspend fun update(trip: Trip) {
        tripDao.updateTrip(trip)
    }

    suspend fun delete(trip: Trip) {
        tripDao.deleteTrip(trip)
    }
}
