package com.example.abouttravel.pages.travel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abouttravel.R
import com.example.abouttravel.adapters.LocalAdapter
import com.example.abouttravel.data.dao.LocationDao
import com.example.abouttravel.data.entities.Local
import com.example.abouttravel.data.entities.Media
import com.example.abouttravel.data.entities.UserLocalRatings
import com.example.abouttravel.databinding.FragmentViewTravelBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ViewTravelFragment : Fragment() {

    private lateinit var binding: FragmentViewTravelBinding
    private lateinit var locationDao: LocationDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewTravelBinding.inflate(inflater, container, false)

        val view = binding.root

        initRecyclerView()

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

    private fun initRecyclerView() {
        val locals: List<Local> = listOf()
        val media: List<Media> = listOf()
        val ratings: List<UserLocalRatings> = listOf()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        // Suponha que seu Adapter tenha um construtor que espera os dados
        binding.recyclerView.adapter = LocalAdapter(locals, media, ratings)
    }

}
