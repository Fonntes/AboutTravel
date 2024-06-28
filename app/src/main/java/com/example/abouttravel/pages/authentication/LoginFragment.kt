package com.example.abouttravel.pages.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R
import com.example.abouttravel.data.entities.LocalType
import com.example.abouttravel.data.entities.Session
import com.example.abouttravel.data.vm.LocalTypeViewModel
import com.example.abouttravel.data.vm.SessionViewModel
import kotlinx.coroutines.launch
import java.util.Date

class LoginFragment : Fragment() {

    private val sessionViewModel: SessionViewModel by viewModels()
    private val localTypeViewModel: LocalTypeViewModel by viewModels()
    private lateinit var errorMessage: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val buttonToRegister = view.findViewById<TextView>(R.id.login_to_register)
        val buttonToHome = view.findViewById<Button>(R.id.buttonLoginToHome)

        errorMessage = view.findViewById(R.id.errorMessage)

        buttonToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }

        buttonToHome.setOnClickListener {
            loginUser(view)
        }

        return view
    }

    private fun loginUser(view: View) {
        val nameField = view.findViewById<EditText>(R.id.nameField)
        val passField = view.findViewById<EditText>(R.id.passwordField)

        val username = nameField.text.toString().trim()
        val password = passField.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.text = getString(R.string.fill_fields_error)
            errorMessage.visibility = View.VISIBLE
            return
        }

        if (username == "User" && password == "Password") {
            Log.d("LoginFragment", "User credentials are valid, proceeding to create session")
            //createAndSaveSession()
            /*lifecycleScope.launch { // Using lifecycleScope for coroutine context
                addDefaultLocalType()
            }*/
            findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
        } else {
            errorMessage.text = getString(R.string.invalid_credentials_error)
            errorMessage.visibility = View.VISIBLE
        }
    }

    private fun createAndSaveSession() {
        val testSession = Session(
            id = 1, // ID fixo para teste
            name = "Test User",
            phoneNumber = "123456789",
            email = "test@example.com",
            profilePicture = "/path/to/profile/picture",
            description = "Test description",
            createdAt = Date(),
            updatedAt = Date(),
            deleteAt = Date()
        )

        Log.d("SessionCreation", "Attempting to insert session with ID: ${testSession.id}")
        lifecycleScope.launch {
            try {
                val insertedId = sessionViewModel.insert(testSession)
                Log.d("SessionCreation", "Session inserted with ID: $insertedId")
            } catch (e: Exception) {
                Log.e("SessionCreation", "Error inserting session", e)
            }
        }
    }

    private suspend fun addDefaultLocalType() {
        val localType = LocalType(
            label = "Restaurante",
            description = "Local para comer",
            createdAt = Date(),
            updatedAt = Date()
        )

        Log.d("LocalTypeInsertion", "Attempting to insert local type with label: ${localType.label}")
        try {
            val insertedId = localTypeViewModel.insert(localType)
            Log.d("LocalTypeInserted", "Inserted ID: $insertedId")
        } catch (e: Exception) {
            Log.e("LocalTypeInsertion", "Error inserting local type", e)
        }
    }
}
