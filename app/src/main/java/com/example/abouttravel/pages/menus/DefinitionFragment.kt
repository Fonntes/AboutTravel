package com.example.abouttravel.pages.menus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DefinitionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_definition, container, false)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)
        val logoutButton = view.findViewById<Button>(R.id.Logout)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    findNavController().navigate(R.id.action_definitionFragment_to_homeFragment)
                    true
                }

                R.id.share -> {
                    findNavController().navigate(R.id.action_definitionFragment_to_shareFragment)
                    true
                }

                R.id.profile -> {
                    findNavController().navigate(R.id.action_definitionFragment_to_profileFragment)
                    true
                }

                else -> false
            }
        }

        logoutButton.setOnClickListener {
            logout()
        }

        return view
    }

    private fun logout() {
        // Limpar a sessão (exemplo com SharedPreferences)
        val sharedPreferences = requireActivity().getSharedPreferences("user_session", 0)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        // Navegar para a tela de login
        findNavController().navigate(R.id.action_definitionFragment2_to_loginFragment)
    }
}
