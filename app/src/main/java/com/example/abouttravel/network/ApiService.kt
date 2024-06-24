package com.example.abouttravel.network
import android.util.Log
import com.example.abouttravel.BuildConfig
import com.example.abouttravel.data.entities.CreateUser
import com.example.abouttravel.data.entities.Local
import com.example.abouttravel.data.entities.Session
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.entities.UserLogin
import okhttp3.ResponseBody
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService(private val tokenManager: TokenManager? = null) {

    private val httpClient: OkHttpClient

    init {
        val httpClientBuilder = OkHttpClient.Builder()

        if (tokenManager != null) {
            val tokenRefresher = TokenRefresher(tokenManager)
            httpClientBuilder.addInterceptor(tokenRefresher.refreshTokenInterceptor)
        }

        httpClient = httpClientBuilder.build()
    }

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.APP_URL)
        .build()

    private val service = retrofit.create(EndPoints::class.java)

    fun home(): Call<ResponseBody> {
        return service.home()
    }

    //auth
    fun authenticateUser(username: String, password: String): Call<ResponseBody> {
        return service.authenticateUser(UserLogin(username, password))
    }

    fun logout(): Call<ResponseBody> {
        return service.logout()
    }

    fun getMe(): Call<ResponseBody> {
        return service.me()
    }

    fun refreshToken(refreshToken: String): Call<ResponseBody> {
        val refreshTokenMap = mapOf("refresh_token" to refreshToken)
        return service.refreshToken(refreshTokenMap)
    }

    fun registerUser(user: CreateUser): Call<ResponseBody> {
        return service.registerUser(user)
    }

    fun deleteUser(): Call<ResponseBody> {
        return service.deleteUser()
    }

    fun updateUser(user: CreateUser): Call<ResponseBody> {
        return service.updateUser(user)
    }

    //user
    fun getUser(id: Int): Call<ResponseBody> {
        return service.user()
    }

    fun getUsers(): Call<ResponseBody> {
        return service.users()
    }

    //trip

    fun getTrips(): Call<ResponseBody> {
        return service.trips()
    }

    fun getTrip(id: Int): Call<ResponseBody> {
        return service.trip()
    }

    fun createTrip(trip: Trip): Call<ResponseBody> {
        return service.createTrip(trip)
    }

    fun updateTrip(trip: Trip): Call<ResponseBody> {
        return service.updateTrip(trip)
    }

    fun deleteTrip(id: Int): Call<ResponseBody> {
        return service.deleteTrip()
    }

    //locals

    fun getLocals(): Call<ResponseBody> {
        return service.locals()
    }

    fun getLocal(id: Int): Call<ResponseBody> {
        return service.local()
    }

    fun createLocal(local: Local): Call<ResponseBody> {
        return service.createLocal(local)
    }

    fun updateLocal(local: Local): Call<ResponseBody> {
        return service.updateLocal(local)
    }

    fun deleteLocal(id: Int): Call<ResponseBody> {
        return service.deleteLocal()
    }
}