package com.example.abouttravel.network
import android.util.Log
import com.example.abouttravel.BuildConfig
import com.example.abouttravel.data.entities.Session
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

    fun authenticateUser(username: String, password: String): Call<ResponseBody> {
        return service.authenticateUser(User(username, password))
    }

    fun getMe(): Call<ResponseBody> {
        return service.me()
    }

    fun refreshToken(refreshToken: String): Call<ResponseBody> {
        val refreshTokenMap = mapOf("refresh_token" to refreshToken)
        return service.refreshToken(refreshTokenMap)
    }

    fun updateUser(session: Session): Call<ResponseBody> {
        return service.updateUser(session)
    }

}