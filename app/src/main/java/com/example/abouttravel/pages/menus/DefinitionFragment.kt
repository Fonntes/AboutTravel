package com.example.abouttravel.pages.menus

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class DefinitionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_definition, container, false)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)

        val backButton = view.findViewById<Button>(R.id.Sobrenos)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_definitionFragment_to_aboutUsFragment)
        }

        val language = view.findViewById<Button>(R.id.language)
        language.setOnClickListener {
            findNavController().navigate(R.id.action_definitionFragment_to_languageFragment3)
        }

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
        return view
    }
}
