package com.example.abouttravel.pages.travel

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.abouttravel.R
import com.example.abouttravel.data.entities.Local
import com.example.abouttravel.data.entities.Trip
import com.example.abouttravel.data.vm.LocationViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CreatePlacesFragment : Fragment() {

    private lateinit var locationViewModel: LocationViewModel
    private lateinit var initialDateField: TextInputEditText
    private var calendar = Calendar.getInstance()
    private lateinit var currentTrip: Trip // Variable to store the current trip
    private lateinit var imageTrip: ImageView
    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null

    // Safe Args to retrieve arguments
    private val args: CreatePlacesFragmentArgs by this.navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_places, container, false)

        // Assign the 'trip' argument from safe args
        currentTrip = args.trip
        initialDateField = view.findViewById(R.id.dateField)
        initialDateField.setOnClickListener {
            showDatePicker(initialDateField)
        }

        imageTrip = view.findViewById(R.id.imageTrip)
        imageTrip.setOnClickListener {
            openImagePicker()
        }

        val saveButton = view.findViewById<ImageView>(R.id.savePlaces)
        saveButton.setOnClickListener {
            saveLocation()
        }

        // Initialize ViewModel using ViewModelProvider
        locationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)

        return view
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

    private fun saveLocation() {
        val nameField = view?.findViewById<TextInputEditText>(R.id.nameField)
        val descriptionField = view?.findViewById<TextInputEditText>(R.id.descriptionField)
        val finalDateField = view?.findViewById<TextInputEditText>(R.id.dateField)
        val localField = view?.findViewById<TextInputEditText>(R.id.localField)

        val title = nameField?.text.toString()
        val description = descriptionField?.text.toString()
        val finalDateString = finalDateField?.text.toString()
        val location = localField?.text.toString()
        val image = imageUri?.toString() ?: ""

        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val finalDate = sdf.parse(finalDateString)

        if (title.isNullOrEmpty() || description.isNullOrEmpty() || finalDate == null || location.isNullOrEmpty()) {
            Toast.makeText(context, "Por favor, preencha todos os campos", Toast.LENGTH_LONG).show()
            return
        }

        // Lançar a corrotina usando viewModelScope
        locationViewModel.viewModelScope.launch {
            try {
                // Criar e inserir o objeto Local
                val local = Local(
                    tripId = currentTrip.id,
                    localTypeId = 1, // Defina o tipo local conforme necessário
                    label = title,
                    latitude = "0.0", // Valores fictícios para latitude e longitude
                    longitude = "0.0",
                    description = description,
                    date = finalDate,
                    createdAt = Date(),
                    updatedAt = Date(),
                    deleteAt = Date()
                )
                locationViewModel.insert(local)

                Toast.makeText(context, "Local salvo com sucesso!", Toast.LENGTH_LONG).show()
                requireActivity().onBackPressed() // Navegar de volta para o fragmento anterior após salvar
            } catch (e: Exception) {
                Toast.makeText(context, "Erro ao salvar o local: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
