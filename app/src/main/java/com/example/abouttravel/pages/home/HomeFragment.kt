package com.example.abouttravel.pages.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abouttravel.R
import com.example.abouttravel.adapters.TravelAdapter
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.vm.SessionViewModel
import com.example.abouttravel.data.vm.TripViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.log

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var travelAdapter: TravelAdapter
    private lateinit var tripViewModel: TripViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sessionViewModel = ViewModelProvider(this).get(SessionViewModel::class.java)
        sessionViewModel.session.observe(viewLifecycleOwner) { session ->
            if (session != null) {
                Log.d("SessionViewModel", "Session updated: ${session.id},,${session.name}, ${session.email}")
            } else {
                Log.d("SessionViewModel", "Session is null")
            }
        }

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)

            val numberOfColumns = calculateNumberOfColumns(110) // 110 é a largura do item em dp
        recyclerView.layoutManager = GridLayoutManager(requireContext(), numberOfColumns)

        travelAdapter = TravelAdapter(emptyList()) { trip ->
            val action = HomeFragmentDirections.actionHomeFragmentToViewTravelFragment2(trip)
            findNavController().navigate(action)
        }
        recyclerView.adapter = travelAdapter

        tripViewModel = ViewModelProvider(this).get(TripViewModel::class.java)
        tripViewModel.allTrips.observe(viewLifecycleOwner, Observer { trips ->
            trips?.let {
                travelAdapter.updateData(it)
            }
        })

        tripViewModel.refreshTrips()

        val trip = Trip()

        trip.label = "test"
        trip.location = "ROMA"
        trip.country_iso2 = "PT"

        Log.e("CreateTrip", "Cum:$trip")

        val trip2 = Trip()

        trip2.id = 4
        trip2.label = "help"
        trip2.location = "ROMA"
        trip2.country_iso2 = "PT"

        //tripViewModel.createTripApi(trip)

        tripViewModel.updateTripApi(trip2)

        tripViewModel.refreshTrips()

        tripViewModel.deleteTripApi(28)

        val add = view.findViewById<ImageView>(R.id.addTravel)
        add.setOnClickListener {
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

    private fun calculateNumberOfColumns(itemWidthInDp: Int): Int {
        val displayMetrics = resources.displayMetrics
        val itemWidthInPx = itemWidthInDp * displayMetrics.density
        val screenWidthInPx = displayMetrics.widthPixels
        return (screenWidthInPx / itemWidthInPx).toInt()
    }
}
