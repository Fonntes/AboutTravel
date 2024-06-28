package com.example.abouttravel.pages.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abouttravel.R
import com.example.abouttravel.adapters.TravelAdapter
import com.example.abouttravel.data.vm.TripViewModel

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
        val numberOfColumns = calculateNumberOfColumns(110) // 110 é a largura do item em dp
        recyclerView.layoutManager = GridLayoutManager(requireContext(), numberOfColumns)

        travelAdapter = TravelAdapter(emptyList()) { trip ->
            val action = HomeFragmentDirections.actionHomeFragmentToViewTravelFragment(trip)
            findNavController().navigate(action)
        }
        recyclerView.adapter = travelAdapter

        tripViewModel = ViewModelProvider(this).get(TripViewModel::class.java)
        tripViewModel.allTrips.observe(viewLifecycleOwner, Observer { trips ->
            trips?.let { travelAdapter.updateData(it) }
        })

        return view
    }

    private fun calculateNumberOfColumns(itemWidthDp: Int): Int {
        val displayMetrics = resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / itemWidthDp).toInt()
    }
}

