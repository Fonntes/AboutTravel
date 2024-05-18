package com.example.abouttravel.pages.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

<<<<<<< HEAD
        val button = view.findViewById<Button>(R.id.button_register_login)
=======
        val button = view.findViewById<Button>(R.id.button_login_to_register)
>>>>>>> 0df13a5f63b27afe63b365b8bad11bd446815160
        button.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }

        return view
    }
}
