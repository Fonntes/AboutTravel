package com.example.abouttravel.pages.menus

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
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class DefinitionFragment : Fragment() {
    companion object {
        val languages = arrayOf("Select Language", "English", "Portuguese")
    }

    private lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_definition, container, false)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)
        val logoutButton = view.findViewById<Button>(R.id.Logout)

        spinner = view.findViewById(R.id.spinner)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedLang = parent.getItemAtPosition(position).toString()
                when (selectedLang) {
                    "Portuguese" -> {
                        setLocale("pt")
                        Toast.makeText(requireContext(), "Portuguese Selected", Toast.LENGTH_SHORT).show()
                    }
                    "English" -> {
                        setLocale("en")
                        Toast.makeText(requireContext(), "English Selected", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

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

        logoutButton.setOnClickListener {
            logout()
        }

        return view
    }

    private fun logout() {
        // Clear the session (example with SharedPreferences)
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

        // Restart activity to apply changes
        val intent = requireActivity().intent
        requireActivity().finish()
        startActivity(intent)
    }
}
