package com.example.abouttravel.api
import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)


    fun isAccessTokenExpired(): Boolean {
        val expiresIn = getAccessTokenExpiresIn()
        val currentTime = System.currentTimeMillis() / 1000
        return currentTime >= expiresIn
    }


    fun saveAccessToken(token: String, expiresIn: Int) {
        with(sharedPreferences.edit()) {
            putString("access_token", token)
            putLong("access_token_expires_in", System.currentTimeMillis() / 1000 + expiresIn * 60) // Convertendo minutos para segundos
            apply()
        }
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString("access_token", null)
    }

    fun getAccessTokenExpiresIn(): Long {
        return sharedPreferences.getLong("access_token_expires_in", 0)
    }
    fun saveRefreshToken(token: String, expiresIn: Int) {
        with(sharedPreferences.edit()) {
            putString("refresh_token", token)
            putLong("refresh_token_expires_in", System.currentTimeMillis() / 1000 + expiresIn * 60) // Convertendo minutos para segundos
            apply()
        }
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString("refresh_token", null)
    }

    fun getRefreshTokenExpiresIn(): Long {
        return sharedPreferences.getLong("refresh_token_expires_in", 0)
    }

    fun removeTokens() {
        val editor = sharedPreferences.edit()
        editor.remove("access_token")
        editor.remove("access_token_expires_in")
        editor.remove("refresh_token")
        editor.remove("refresh_token_expires_in")
        editor.apply()
    }
}