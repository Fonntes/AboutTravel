package com.example.abouttravel

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.abouttravel.network.ApiService
import com.example.abouttravel.network.TokenManager
import com.example.abouttravel.pages.IntroSlider.IntroActivity
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs: SharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val isFirstRun = prefs.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            // Redireciona para IntroActivity
            startActivity(Intent(this, IntroActivity::class.java))
            // Marca que já não é mais a primeira execução
            val editor = prefs.edit()
            editor.putBoolean("isFirstRun", false)
            editor.apply()
        } else {
            // Continue para a atividade principal
            setContentView(R.layout.activity_main)

            val tokenManager = TokenManager(this)
            tokenManager.removeTokens()

            if (tokenManager.getAccessToken() != null) {
                println("token guardado no local" + tokenManager.getAccessToken())
            } else {
                val apiService = ApiService()
                val call = apiService.authenticateUser("jhon", "123456789")

                call.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()?.string()
                            val jsonObject = JSONObject(responseBody)
                            val dataObject = jsonObject.getJSONObject("data")
                            val tokenObject = dataObject.getJSONObject("token")
                            val refreshTokenObject = dataObject.getJSONObject("refresh_token")
                            val token = tokenObject.getString("token")
                            val expiresIn = tokenObject.getInt("expires_in")
                            val refreshToken = refreshTokenObject.getString("token")
                            val refreshExpiresIn = refreshTokenObject.getInt("expires_in")
                            tokenManager.saveAccessToken(token, expiresIn)
                            tokenManager.saveRefreshToken(refreshToken, refreshExpiresIn)
                            println("Authentication Access token: ${tokenManager.getAccessToken()}")
                            println("Authentication Refresh token: $refreshToken")

                            val handler = Handler(Looper.getMainLooper())
                            handler.postDelayed({
                                val apiServiceWithToken = ApiService(tokenManager)
                                val callGetMe = apiServiceWithToken.getMe()

                                callGetMe.enqueue(object : Callback<ResponseBody> {
                                    override fun onResponse(
                                        call: Call<ResponseBody>,
                                        response: Response<ResponseBody>
                                    ) {
                                        if (response.isSuccessful) {
                                            val responseBody = response.body()
                                            println(responseBody?.string())
                                        }
                                    }

                                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                        t.printStackTrace()
                                    }
                                })
                            }, 1)
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            }
        }
    }
}
