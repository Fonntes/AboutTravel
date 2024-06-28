package com.example.abouttravel.pages.menus

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class LanguageFragment : Fragment() {
    private lateinit var buttonEn: Button
    private lateinit var buttonPt: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_language, container, false)

        buttonEn = view.findViewById(R.id.buttonen)
        buttonPt = view.findViewById(R.id.buttonpt)
        val backButton = view.findViewById<Button>(R.id.back)

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_languageFragment_to_definitionFragment)
        }

        buttonEn.setOnClickListener {
            setLocale("en")
            updateButtonState()
        }

        buttonPt.setOnClickListener {
            setLocale("pt")
            updateButtonState()
        }

        // Set the initial state of buttons
        updateButtonState()

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findNavController().navigate(R.id.action_definitionFragment_to_homeFragment)
                    true
                }
                R.id.share -> {
                    findNavController().navigate(R.id.action_definitionFragment_to_shareFragment)
                    true
                }
                R.id.profile -> {
                    findNavController().navigate(R.id.action_definitionFragment_to_profileFragment)
                    true
                }
                else -> false
            }
        }

        return view
    }

    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        requireActivity().baseContext.resources.updateConfiguration(config, requireActivity().baseContext.resources.displayMetrics)

        // Save language to SharedPreferences
        val prefs = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("language", language)
        editor.apply()

        // Reload the activity to apply language change
        requireActivity().recreate()
    }

    private fun updateButtonState() {
        val prefs = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val language = prefs.getString("language", "pt") ?: "pt"

        if (language == "en") {
            buttonEn.isEnabled = false
            buttonPt.isEnabled = true
        } else {
            buttonEn.isEnabled = true
            buttonPt.isEnabled = false
        }
    }
}
