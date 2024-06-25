package com.example.abouttravel.pages.authentication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R
import com.example.abouttravel.api.ApiService
import com.example.abouttravel.api.TokenManager
import com.example.abouttravel.data.entities.Session
import com.example.abouttravel.data.vm.SessionViewModel
import com.example.abouttravel.helpers.DateConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import com.example.abouttravel.helpers.Network
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

class LoginFragment : Fragment() {

    private val sessionViewModel: SessionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val network = Network().isNetworkAvailable(requireContext())
        val tokenManager = TokenManager(requireContext())
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val buttonToRegister = view.findViewById<TextView>(R.id.login_to_register)
        val buttonToHome = view.findViewById<Button>(R.id.buttonLoginToHome)
        //tokenManager.removeTokens()
        val accessToken = tokenManager.getAccessToken()

        if (network) {
            if (accessToken == null) {
                Toast.makeText(requireContext(), "Validação expirada", Toast.LENGTH_SHORT).show()
            } else if (tokenManager.isAccessTokenExpired()) {
                getSession()
            } else {
                getSession()
            }
        } else {
            Toast.makeText(requireContext(), "Sem conexão com a internet", Toast.LENGTH_SHORT)
                .show()
            //fazer o login offline sem precisar dos dados e passar para home
        }

        buttonToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }

        buttonToHome.setOnClickListener {
            loginUser(view)
        }
        return view
    }

    private fun loginUser(view: View) {

        val tokenManager = TokenManager(requireContext())

        val nameField = view.findViewById<EditText>(R.id.nameField)
        val passField = view.findViewById<EditText>(R.id.passField)

        val username = nameField.text.toString().trim()
        val password = passField.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val apiService = ApiService()
        val call = apiService.authenticateUser(username, password)
        //val call = apiService.authenticateUser("Jhon", "123456789")

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
                    getSession()
                }else
                Toast.makeText(requireContext(), "Login failed: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(requireContext(), "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
                }
                Log.e("LoginFragment", "Login failed: ${t.message}")
            }
        })
    }

    private fun getSession() {
        if (Network().isNetworkAvailable(requireContext())) {
            val tokenManager = TokenManager(requireContext())
            val apiServiceWithToken = ApiService(tokenManager)
            val callGetMe = apiServiceWithToken.getMe()

            callGetMe.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        try {
                            val responseBody = response.body()?.string()
                            val jsonObject = JSONObject(responseBody)

                            if (jsonObject.getString("status") == "Authenticated") {
                                val userObject = jsonObject.getJSONObject("user")

                                val session = Session(
                                    id = userObject.getInt("id"),
                                    name = userObject.getString("name"),
                                    phoneNumber = userObject.getString("phone_number"),
                                    email = userObject.getString("email"),
                                    profilePicture = userObject.getString("profile_picture"),
                                    description = userObject.getString("description"),

                                    createdAt = DateConverter.fromString(userObject.getString("created_at")) ?: Date(),
                                    updatedAt = DateConverter.fromString(userObject.getString("updated_at")) ?: Date(),
                                    //deleteAt = if (userObject.isNull("deleted_at")) Date() else DateConverter.fromString(userObject.getString("deleted_at")) ?: Date()

                                )
                                sessionViewModel.deleteAll()
                                sessionViewModel.insert(session)
                                println("Session: $session")

                                findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Erro na autenticação",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } catch (e: JSONException) {
                            Toast.makeText(
                                requireContext(),
                                "Erro ao analisar a resposta da API",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Erro na requisição à API",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(requireContext(), "Erro de rede", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(requireContext(), "Sem conexão com a internet", Toast.LENGTH_SHORT)
                .show()
        }
    }
}

