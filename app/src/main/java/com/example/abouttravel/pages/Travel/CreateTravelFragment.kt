package com.example.abouttravel.pages.Travel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class CreateTravelFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_definition, container, false)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    findNavController().navigate(R.id.action_shareFragment_to_homeFragment)
                    true
                }

                R.id.profile -> {
                    findNavController().navigate(R.id.action_shareFragment_to_profileFragment)
                    true
                }
                else -> false
            }
        }

        return view
    }
}