package com.example.abouttravel.pages.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val buttonToRegister = view.findViewById<TextView>(R.id.login_to_register)

        buttonToRegister.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }

        val buttonToHome = view.findViewById<Button>(R.id.buttonLoginToHome)

        buttonToHome.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
        }

        return view
    }
}
