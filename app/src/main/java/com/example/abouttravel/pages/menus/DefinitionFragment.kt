package com.example.abouttravel.pages.menus

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.MainActivity
import com.example.abouttravel.R
import com.example.abouttravel.api.TokenManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class DefinitionFragment : Fragment() {
    companion object {
        val languages = arrayOf("Select Language", "English", "Portuguese")
    }

    private lateinit var spinner: Spinner
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_definition, container, false)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)
        val logoutButton = view.findViewById<Button>(R.id.Logout)

        spinner = view.findViewById(R.id.spinner)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("user_settings", Context.MODE_PRIVATE)

        // Setup spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Retrieve selected language from SharedPreferences
        val selectedLanguage = sharedPreferences.getString("language", "en") ?: "en"
        spinner.setSelection(getLanguageIndex(selectedLanguage))

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedLang = parent.getItemAtPosition(position).toString()
                when (selectedLang) {
                    "Portuguese" -> {
                        setLocale("pt")
                        saveLanguage("pt")
                        Toast.makeText(requireContext(), "Portuguese Selected", Toast.LENGTH_SHORT).show()
                    }
                    "English" -> {
                        setLocale("en")
                        saveLanguage("en")
                        Toast.makeText(requireContext(), "English Selected", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
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

        logoutButton.setOnClickListener {
            logout()
        }

        return view
    }

    private fun logout() {
        // Clear the session (example with SharedPreferences)
        val tokenManager = TokenManager(requireContext())
        tokenManager.removeTokens()
        val sharedPreferences = requireActivity().getSharedPreferences("user_session", 0)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        // Navigate to the login screen
        findNavController().navigate(R.id.action_definitionFragment2_to_loginFragment)
    }

    private fun setLocale(langCode: String) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val resources: Resources = requireContext().resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // Save the updated configuration to persist across app restarts
        requireActivity().applicationContext.resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun saveLanguage(langCode: String) {
        val editor = sharedPreferences.edit()
        editor.putString("language", langCode)
        editor.apply()
    }

    private fun getLanguageIndex(langCode: String): Int {
        return when (langCode) {
            "pt" -> 2
            else -> 1 // Default to English
        }
    }
}
