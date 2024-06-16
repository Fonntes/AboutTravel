package com.example.abouttravel.pages.travel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.vm.SessionViewModel
import com.example.abouttravel.data.vm.TripViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Date

class CreateTravelFragment : Fragment() {

    private val tripViewModel: TripViewModel by viewModels()
    private val sessionViewModel: SessionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_travel, container, false)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findNavController().navigate(R.id.action_createTravelFragment2_to_homeFragment)
                    true
                }
                R.id.share -> {
                    findNavController().navigate(R.id.action_createTravelFragment2_to_shareFragment)
                    true
                }
                R.id.profile -> {
                    findNavController().navigate(R.id.action_createTravelFragment2_to_profileFragment)
                    true
                }
                else -> false
            }
        }

        val saveButton = view.findViewById<ImageView>(R.id.savetrip)
        saveButton.setOnClickListener {
            Log.d("CreateTravelFragment", "Save button clicked")
            saveTrip(view)
        }

        return view
    }

    private fun saveTrip(view: View) {
        val nameField = view.findViewById<EditText>(R.id.nameField)
        val descriptionField = view.findViewById<EditText>(R.id.descriptionField)
        val initialDateField = view.findViewById<EditText>(R.id.initialDateField)
        val finalDateField = view.findViewById<EditText>(R.id.finalDateField)
        val localField = view.findViewById<EditText>(R.id.localField)
        val classificationField = view.findViewById<EditText>(R.id.classificationField)

        val title = nameField.text.toString()
        val description = descriptionField.text.toString()
        val initialDate = Date() // Atualizar com a lógica de conversão de data correta
        val finalDate = Date() // Atualizar com a lógica de conversão de data correta
        val location = localField.text.toString()
        val classification = classificationField.text.toString()

        Log.d("CreateTravelFragment", "Preparing to observe session")
        sessionViewModel.session.observe(viewLifecycleOwner, Observer { session ->
            if (session == null) {
                Log.d("CreateTravelFragment", "Session not found")
                Toast.makeText(context, "Erro: Sessão do usuário não encontrada!", Toast.LENGTH_LONG).show()
                return@Observer
            }

            val trip = Trip(
                userId = session.id,
                title = title,
                description = description,
                date = initialDate,
                country = "Unknown", // Ajustar conforme necessário
                location = location,
                latitude = "0.0", // Ajustar conforme necessário
                longitude = "0.0", // Ajustar conforme necessário
                isShared = false,
                createdAt = Date(),
                deleteAt = Date(),
                updatedAt = Date()
            )

            Log.d("CreateTravelFragment", "Inserting trip: $trip")
            tripViewModel.insert(trip)
            Toast.makeText(context, "Viagem salva com sucesso!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_createTravelFragment2_to_homeFragment)
        })
    }
}
