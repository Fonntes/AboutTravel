package com.example.abouttravel.pages.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_register, container, false)

<<<<<<< HEAD
        /*val button = view.findViewById<Button>(R.id.button_register_login)
        button.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }*/
=======
        val button = view.findViewById<Button>(R.id.button_register_to_login)
        button.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment2_to_loginFragment)
        }
>>>>>>> 0df13a5f63b27afe63b365b8bad11bd446815160

        return view
    }
}