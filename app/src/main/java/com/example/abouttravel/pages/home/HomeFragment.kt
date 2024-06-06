package com.example.abouttravel.pages.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R
import com.example.abouttravel.databinding.FragmentCreateTravelBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val add = view.findViewById<ImageView>(R.id.addTravel)

        add.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_createTravelFragment2)
        }

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.share -> {
                    findNavController().navigate(R.id.action_homeFragment_to_shareFragment)
                    true
                }

                R.id.profile -> {
                    findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                    true
                }
                else -> false
            }
        }

        return view
    }
}
