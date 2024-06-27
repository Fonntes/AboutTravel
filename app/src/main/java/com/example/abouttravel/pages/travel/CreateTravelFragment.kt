package com.example.abouttravel.pages.travel

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CreateTravelFragment : Fragment() {

    private val tripViewModel: TripViewModel by viewModels()
    private val sessionViewModel: SessionViewModel by viewModels()
    private lateinit var imageTrip: ImageView
    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null
    private lateinit var initialDateField: EditText
    private lateinit var finalDateField: EditText
    private var calendar = Calendar.getInstance()

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

        initialDateField = view.findViewById(R.id.initialDateField)
        finalDateField = view.findViewById(R.id.finalDateField)

        initialDateField.setOnClickListener {
            showDatePicker(initialDateField)
        }

        finalDateField.setOnClickListener {
            showDatePicker(finalDateField)
        }

        val saveButton = view.findViewById<ImageView>(R.id.savetrip)
        saveButton.setOnClickListener {
            Log.d("CreateTravelFragment", "Save button clicked")
            saveTrip(view)
        }

        imageTrip = view.findViewById(R.id.imageTrip)
        imageTrip.setOnClickListener {
            openImagePicker()
        }

        return view
    }


    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            imageTrip.setImageURI(imageUri)
        }
    }

    private fun saveTrip(view: View) {
        val nameField = view.findViewById<EditText>(R.id.nameField)
        val descriptionField = view.findViewById<EditText>(R.id.descriptionField)
        val localField = view.findViewById<EditText>(R.id.localField)
        val classificationField = view.findViewById<EditText>(R.id.classificationField)

        val title = nameField.text.toString()
        val description = descriptionField.text.toString()
        val initialDateText = initialDateField.text.toString()
        val finalDateText = finalDateField.text.toString()
        val location = localField.text.toString()
        val classification = classificationField.text.toString()
        val image = imageUri?.toString() ?: ""

        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val initialDate = sdf.parse(initialDateText)
        val finalDate = sdf.parse(finalDateText)

        Log.d("CreateTravelFragment", "Preparing to observe session")
        sessionViewModel.session.observe(viewLifecycleOwner, Observer { session ->
            if (session == null) {
                Log.d("CreateTravelFragment", "Session not found")
                Toast.makeText(context, "Erro: Sessão do usuário não encontrada!", Toast.LENGTH_LONG).show()
                return@Observer
            }

            val trip = Trip(
                userId = session.id,
                label = title,
                description = description,
                initial_date = initialDate,
                end_date = finalDate,
                country_iso2 = "Unknown",
                location = location,
                latitude = "0.0",
                longitude = "0.0",
                isShared = false,
                createdAt = Date(),
                deleteAt = Date(),
                updatedAt = Date(),
                image = image,
            )

            Log.d("CreateTravelFragment", "Inserting trip: $trip")
            tripViewModel.insert(trip)
            Toast.makeText(context, "Viagem salva com sucesso!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_createTravelFragment2_to_homeFragment)
        })
    }
    private fun showDatePicker(editText: EditText) {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                editText.setText(sdf.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }


}
