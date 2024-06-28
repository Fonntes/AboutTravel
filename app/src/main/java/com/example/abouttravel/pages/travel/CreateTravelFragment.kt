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
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
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
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
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
    private lateinit var initialDateField: TextInputEditText
    private lateinit var finalDateField: TextInputEditText
    private var calendar = Calendar.getInstance()
    private lateinit var errorMessageName: TextView
    private lateinit var errorMessageDescription: TextView
    private lateinit var errorMessageDateInitial: TextView
    private lateinit var errorMessageDateFinal: TextView
    private lateinit var errorMessageCountry: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_travel, container, false)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)

        errorMessageName = view.findViewById(R.id.errorMessageName)
        errorMessageDescription = view.findViewById(R.id.errorMessageDescription)
        errorMessageDateInitial = view.findViewById(R.id.errorMessageDateInitial)
        errorMessageDateFinal = view.findViewById(R.id.errorMessageDateFinal)
        errorMessageCountry = view.findViewById(R.id.errorMessageCountry)

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findNavController().navigate(R.id.action_createTravelFragment_to_homeFragment)
                    true
                }
                R.id.share -> {
                    findNavController().navigate(R.id.action_createTravelFragment_to_shareFragment)
                    true
                }
                R.id.profile -> {
                    findNavController().navigate(R.id.action_createTravelFragment_to_profileFragment)
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
        val nameField = view.findViewById<TextInputEditText>(R.id.nameField)
        val descriptionField = view.findViewById<TextInputEditText>(R.id.descriptionField)
        val initialDateField = view.findViewById<TextInputEditText>(R.id.initialDateField)
        val finalDateField = view.findViewById<TextInputEditText>(R.id.finalDateField)
        val localField = view.findViewById<TextInputEditText>(R.id.localField)

        val title = nameField.text.toString()
        val description = descriptionField.text.toString()
        val initialDateText = initialDateField.text.toString()
        val finalDateText = finalDateField.text.toString()
        val location = localField.text.toString()
        val image = imageUri?.toString() ?: ""

        // Validar se todos os campos estão preenchidos
        if (title.isEmpty() || description.isEmpty() || initialDateText.isEmpty() || finalDateText.isEmpty() || location.isEmpty()) {

            if (title.isEmpty()) {
                errorMessageName.text = getString(R.string.field_required)
                errorMessageName.visibility = View.VISIBLE
            } else {
                errorMessageName.visibility = View.GONE
            }

            if (description.isEmpty()) {
                errorMessageDescription.text = getString(R.string.field_required)
                errorMessageDescription.visibility = View.VISIBLE
            } else {
                errorMessageDescription.visibility = View.GONE
            }

            if (initialDateText.isEmpty()) {
                errorMessageDateInitial.text = getString(R.string.field_required)
                errorMessageDateInitial.visibility = View.VISIBLE
            } else {
                errorMessageDateInitial.visibility = View.GONE
            }

            if (finalDateText.isEmpty()) {
                errorMessageDateFinal.text = getString(R.string.field_required)
                errorMessageDateFinal.visibility = View.VISIBLE
            } else {
                errorMessageDateFinal.visibility = View.GONE
            }

            if (location.isEmpty()) {
                errorMessageCountry.text = getString(R.string.field_required)
                errorMessageCountry.visibility = View.VISIBLE
            } else {
                errorMessageCountry.visibility = View.GONE
            }

            return
        }

        // Validar se a data final é posterior à data inicial
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        try {
            val initialDate = sdf.parse(initialDateText)
            val finalDate = sdf.parse(finalDateText)

            if (finalDate.before(initialDate)) {

                errorMessageDateFinal.text = getString(R.string.date_error)
                errorMessageDateFinal.visibility = View.VISIBLE

                return
            } else {
                errorMessageDateFinal.visibility = View.GONE
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            Toast.makeText(context, "Erro ao converter datas!", Toast.LENGTH_SHORT).show()
            return
        }


        sessionViewModel.session.observe(viewLifecycleOwner, Observer { session ->
            if (session == null) {
                Toast.makeText(context, "Erro: Sessão do usuário não encontrada!", Toast.LENGTH_LONG).show()
                return@Observer
            }

            val trip = Trip(
                userId = session.id,
                title = title,
                description = description,
                initialdate = sdf.parse(initialDateText),
                enddate = sdf.parse(finalDateText),
                country = "Unknown",
                location = location,
                latitude = "0.0",
                longitude = "0.0",
                isShared = false,
                createdAt = Date(),
                deleteAt = Date(),
                updatedAt = Date(),
                image = image,
            )

            tripViewModel.insert(trip)
            Toast.makeText(context, "Viagem salva com sucesso!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_createTravelFragment_to_homeFragment)
        })
    }


    private fun showDatePicker(editText: TextInputEditText) {
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
