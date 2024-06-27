package com.example.abouttravel.pages.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R
import com.example.abouttravel.api.ApiService
import com.example.abouttravel.api.TokenManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var apiService: ApiService
    private lateinit var nameField: TextInputEditText
    private lateinit var usernameField: TextInputEditText
    private lateinit var numberField: TextInputEditText
    private lateinit var emailField: TextInputEditText
    private lateinit var descriptionField: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val settings = view.findViewById<ImageView>(R.id.settings)

        settings.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_definitionFragment)
        }

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
                    true
                }
                R.id.share -> {
                    findNavController().navigate(R.id.action_profileFragment_to_shareFragment)
                    true
                }
                else -> false
            }
        }

        // Inicializar as views
        nameField = view.findViewById(R.id.nameField)
        usernameField = view.findViewById(R.id.usernameField)
        numberField = view.findViewById(R.id.numberField)
        emailField = view.findViewById(R.id.emailField)
        descriptionField = view.findViewById(R.id.descriptionField)

        // Inicializar ApiService com TokenManager
        apiService = ApiService(TokenManager(requireContext()))

        // Atualizar perfil ao carregar fragmento
        updateProfile()

        return view
    }

    private fun updateProfile() {
        apiService.getMe().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.string()
                    Log.d("ProfileFragment", "Response body: $responseBody")
                    // Atualizar UI com dados do usuário
                    updateProfileUI(responseBody)
                } else {
                    Log.e("ProfileFragment", "Erro na resposta da API: ${response.code()} ${response.message()}")
                    // Exemplo: exibir mensagem de erro
                    // Toast.makeText(requireContext(), "Erro ao carregar perfil", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("ProfileFragment", "Falha na requisição", t)
                // Exemplo: exibir mensagem de erro
                // Toast.makeText(requireContext(), "Falha na requisição", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateProfileUI(response: String?) {
        response?.let {
            try {
                val jsonObject = JSONObject(it)
                val userObject = jsonObject.getJSONObject("user")

                val name = userObject.optString("name", "N/A")
                val username = userObject.optString("username", "N/A")
                val number = userObject.optString("phone_number", "N/A")
                val email = userObject.optString("email", "N/A")
                val description = userObject.optString("description", "N/A")

                Log.d("ProfileFragment", "Name: $name, Username: $username, Number: $number, Email: $email, Description: $description")

                // Atualizar as views com os dados extraídos
                nameField.setText(name)
                usernameField.setText(username)
                numberField.setText(number)
                emailField.setText(email)
                descriptionField.setText(description)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
}
