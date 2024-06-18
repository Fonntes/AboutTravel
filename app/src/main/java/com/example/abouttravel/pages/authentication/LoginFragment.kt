package com.example.abouttravel.pages.authentication

import android.os.Bundle
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
import com.example.abouttravel.data.entities.Session
import com.example.abouttravel.data.vm.SessionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class LoginFragment : Fragment() {

    private val sessionViewModel: SessionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val buttonToRegister = view.findViewById<TextView>(R.id.login_to_register)
        val buttonToHome = view.findViewById<Button>(R.id.buttonLoginToHome)

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
        val passField = view.findViewById<EditText>(R.id.passField)

        val username = nameField.text.toString().trim()
        val password = passField.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        // Simulação de sucesso no login (você deve implementar a lógica real de login aqui)
        if (username == "User" && password == "Password") {
            // Criar e salvar a sessão
            //createAndSaveSession()

            // Navegar para a próxima tela (homeActivity no exemplo)
            findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
        } else {
            Toast.makeText(context, "Credenciais inválidas!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createAndSaveSession() {
        // Criar uma sessão de exemplo (você pode ajustar conforme necessário)
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

        // Salvar a sessão usando o ViewModel
        CoroutineScope(Dispatchers.Main).launch {
            sessionViewModel.insert(testSession)
        }
    }
}
