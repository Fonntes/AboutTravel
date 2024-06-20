package com.example.abouttravel.pages.travel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abouttravel.R
import com.example.abouttravel.adapters.LocalAdapter
import com.example.abouttravel.data.entities.Local
import com.example.abouttravel.data.entities.Media
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.entities.UserLocalRatings
import com.example.abouttravel.databinding.FragmentViewTravelBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Locale

class ViewTravelFragment : Fragment() {

    private lateinit var binding: FragmentViewTravelBinding
    private val args: ViewTravelFragmentArgs by navArgs() // Recebe os argumentos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewTravelBinding.inflate(inflater, container, false)
        val view = binding.root

        val trip = args.trip // Obtem os dados da viagem dos argumentos
        displayTripDetails(trip)

        initRecyclerView()

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findNavController().navigate(R.id.action_viewTravelFragment_to_homeFragment)
                    true
                }
                R.id.profile -> {
                    findNavController().navigate(R.id.action_viewTravelFragment_to_profileFragment)
                    true
                }
                R.id.share -> {
                    findNavController().navigate(R.id.action_viewTravelFragment_to_shareFragment)
                    true
                }
                else -> false
            }
        }

        return view
    }

    private fun displayTripDetails(trip: Trip) {
        binding.profileImage1.setImageResource(R.drawable.profile)
        binding.tripTitle.text = trip.title
        binding.tripLocation.text = trip.location
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(trip.date)
        binding.tripDate.text = formattedDate
        binding.tripDescription.text = trip.description
    }

    private fun initRecyclerView() {
        val locals: List<Local> = listOf()
        val media: List<Media> = listOf()
        val ratings: List<UserLocalRatings> = listOf()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = LocalAdapter(locals, media, ratings)
    }
}
