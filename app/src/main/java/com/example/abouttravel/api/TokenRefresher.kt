package com.example.abouttravel.api


import okhttp3.Interceptor
import org.json.JSONObject

class TokenRefresher(private val tokenManager: TokenManager) {

    val refreshTokenInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()

        // Verificar se a solicitação é para atualizar o token
        if (originalRequest.url().toString().endsWith("auth/refresh")) {
            return@Interceptor chain.proceed(originalRequest)
        }

        // Verificar se o token de acesso expirou
        if (tokenManager.isAccessTokenExpired()) {
            val refreshToken = tokenManager.getRefreshToken()
            if (refreshToken != null) {
                val apiService = ApiService(tokenManager) // Crie a instância de ApiService aqui
                val callRefreshToken = apiService.refreshToken(refreshToken)
                val responseRefreshToken = callRefreshToken.execute()

                if (responseRefreshToken.isSuccessful) {
                    val responseBody = responseRefreshToken.body()?.string()
                    val jsonObject = JSONObject(responseBody)
                    val dataObject = jsonObject.getJSONObject("data")

                    val tokenObject = dataObject.getJSONObject("token")
                    val newAccessToken = tokenObject.getString("token")
                    val expiresIn = tokenObject.getInt("expires_in")
                    tokenManager.saveAccessToken(newAccessToken, expiresIn)
                    println("Access token update: $newAccessToken")

                    val refreshTokenObject = dataObject.getJSONObject("refresh_token")
                    val newRefreshToken = refreshTokenObject.getString("token")
                    val refreshExpiresIn = refreshTokenObject.getInt("expires_in")
                    tokenManager.saveRefreshToken(newRefreshToken, refreshExpiresIn)
                    println("Refresh token update: $newRefreshToken")
                }
            }
        }

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${tokenManager.getAccessToken()}")
            .build()

        chain.proceed(newRequest)
    }
}