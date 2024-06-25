package com.example.abouttravel.pages.authentication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R
import com.example.abouttravel.api.ApiService
import com.example.abouttravel.helpers.CreateUser
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val registerButton = view.findViewById<Button>(R.id.buttonRegister)
        val loginButton = view.findViewById<TextView>(R.id.register_to_login)

        registerButton.setOnClickListener {
            register(view)
        }

        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment2_to_loginFragment)
        }

        return view
    }

    private fun register(view: View) {

        val nameField = view.findViewById<EditText>(R.id.nameField)
        val emailField = view.findViewById<EditText>(R.id.emailField)
        val passField = view.findViewById<EditText>(R.id.passwordField)
        val passConfirmField = view.findViewById<EditText>(R.id.confirmPassField)

        val errorMessage = view.findViewById<TextView>(R.id.errorMessage)

        val username = nameField.text.toString().trim()
        val email = emailField.text.toString().trim()
        val password = passField.text.toString().trim()
        val confirmPassword = passConfirmField.text.toString().trim()

        errorMessage.visibility = View.GONE

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorMessage.visibility = View.VISIBLE
            errorMessage.text = getString(R.string.fill_fields_error)
            return
        }

        if (password != confirmPassword) {
            errorMessage.visibility = View.VISIBLE
            errorMessage.text = getString(R.string.fill_fields_error)
            return
        }

        val user = CreateUser(username,username,password,email)

        val apiService = ApiService()
        val call = apiService.registerUser(user)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.string()
                    val jsonObject = JSONObject(responseBody)

                    if (jsonObject.getString("status") == "success") {

                        Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                        println("Registration successful")
                        findNavController().navigate(R.id.action_registerFragment2_to_loginFragment)

                    } else {
                        val errorMessage = try {
                            jsonObject.getString("message")
                        } catch (e: JSONException) {
                            "Registration failed"
                        }
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Registration failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(requireContext(), "Registration failed: ${t.message}", Toast.LENGTH_SHORT).show()
                }
                Log.e("RegisterFragment", "Registration failed: ${t.message}")
            }
        })
    }

}