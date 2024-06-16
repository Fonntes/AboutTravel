package com.example.abouttravel.pages.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abouttravel.R
import com.example.abouttravel.adapters.TravelAdapter
import com.example.abouttravel.data.vm.TripViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var travelAdapter: TravelAdapter
    private lateinit var tripViewModel: TripViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        travelAdapter = TravelAdapter(emptyList()) // Inicialmente com lista vazia
        recyclerView.adapter = travelAdapter

        // Configuração do ViewModel para obter dados de viagens
        tripViewModel = ViewModelProvider(this).get(TripViewModel::class.java)
        tripViewModel.allTrips.observe(viewLifecycleOwner, Observer { trips ->
            trips?.let {
                travelAdapter.updateData(it)
            }
        })

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
