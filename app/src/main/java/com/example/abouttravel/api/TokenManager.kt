package com.example.abouttravel.api
import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {

    companion object {
        private const val PREFS_NAME = "auth_tokens"
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_ACCESS_TOKEN_EXPIRES_IN = "access_token_expires_in"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_REFRESH_TOKEN_EXPIRES_IN = "refresh_token_expires_in"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun isAccessTokenExpired(): Boolean {
        val expiresIn = getAccessTokenExpiresIn()
        val currentTime = System.currentTimeMillis()
        return currentTime >= expiresIn
    }


    fun saveAccessToken(token: String, expiresIn: Int) {
        with(sharedPreferences.edit()) {
            putString(KEY_ACCESS_TOKEN, token)
            putLong(KEY_ACCESS_TOKEN_EXPIRES_IN, System.currentTimeMillis() + expiresIn * 1000L)
            apply()
        }
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
    }

    fun getAccessTokenExpiresIn(): Long {
        return sharedPreferences.getLong(KEY_ACCESS_TOKEN_EXPIRES_IN, 0)
    }
    fun saveRefreshToken(token: String, expiresIn: Int) {
        with(sharedPreferences.edit()) {
            putString(KEY_REFRESH_TOKEN, token)
            putLong(KEY_REFRESH_TOKEN_EXPIRES_IN, System.currentTimeMillis() + expiresIn * 1000L)
            apply()
        }
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(KEY_REFRESH_TOKEN, null)
    }

    fun getRefreshTokenExpiresIn(): Long {
        return sharedPreferences.getLong(KEY_REFRESH_TOKEN_EXPIRES_IN, 0)
    }

    fun removeTokens() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_ACCESS_TOKEN)
        editor.remove(KEY_ACCESS_TOKEN_EXPIRES_IN)
        editor.remove(KEY_REFRESH_TOKEN)
        editor.remove(KEY_REFRESH_TOKEN_EXPIRES_IN)
        editor.apply()
    }
}
