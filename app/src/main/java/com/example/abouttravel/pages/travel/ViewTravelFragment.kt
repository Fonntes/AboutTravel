package com.example.abouttravel.pages.travel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abouttravel.R
import com.example.abouttravel.adapters.LocalAdapter
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.vm.LocationViewModel
import com.example.abouttravel.databinding.FragmentViewTravelBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Locale

class ViewTravelFragment : Fragment() {

    private lateinit var binding: FragmentViewTravelBinding
    private val args: ViewTravelFragmentArgs by navArgs() // Recebe os argumentos
    private lateinit var locationViewModel: LocationViewModel // ViewModel para locais

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewTravelBinding.inflate(inflater, container, false)
        val view = binding.root

        val trip = args.trip // Obtém os dados da viagem dos argumentos
        displayTripDetails(trip)

        // Inicializa o ViewModel usando ViewModelProvider
        locationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)

        // Inicializa o RecyclerView com os locais da viagem
        initRecyclerView(trip)

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

        val addPlaceButton = view.findViewById<ImageView>(R.id.addPlace)
        addPlaceButton.setOnClickListener {
            val action = ViewTravelFragmentDirections.actionViewTravelFragmentToCreatePlacesFragment(trip)
            findNavController().navigate(action)
        }

        return view
    }

    private fun displayTripDetails(trip: Trip) {
        // Exibe os detalhes da viagem nos componentes de UI correspondentes
        binding.profileImage1.setImageResource(R.drawable.profile)
        binding.tripTitle.text = trip.title
        binding.tripLocation.text = trip.location

        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val initialDate = dateFormat.format(trip.initialdate)
        val endDate = dateFormat.format(trip.enddate)

        binding.tripDate.text = "$initialDate"
        binding.finalDate.text =  "$endDate"
        binding.tripDescription.text = trip.description
    }

    private fun initRecyclerView(trip: Trip) {
        // Observa os locais associados à viagem do ViewModel
        locationViewModel.getLocationsForTrip(trip.id).observe(viewLifecycleOwner) { locals ->
            locals?.let {
                // Configura o RecyclerView com os locais obtidos
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerView.setHasFixedSize(true)
                binding.recyclerView.adapter = LocalAdapter(it, emptyList(), emptyList())
            }
        }
    }
}
