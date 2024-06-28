package com.example.abouttravel.pages.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.abouttravel.R
import com.example.abouttravel.data.entities.Session
import com.example.abouttravel.data.vm.SessionViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment() {
    private lateinit var sessionViewModel: SessionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inicialização do ViewModel
        sessionViewModel = ViewModelProvider(this).get(SessionViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Observa mudanças na sessão e atualiza UI
        sessionViewModel.session.observe(viewLifecycleOwner, Observer { session ->
            session?.let {
                // Atualiza a UI com os dados da sessão
                updateProfileUI(session)
            }
        })

        val settings = view.findViewById<ImageView>(R.id.settings)

        settings.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_definitionFragment)
        }

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.navbar)

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
                    true
                }

                R.id.share -> {
                    findNavController().navigate(R.id.action_profileFragment_to_shareFragment)
                    true
                }
                else -> false
            }
        }

        return view
    }

    private fun updateProfileUI(session: Session) {
        view?.findViewById<EditText>(R.id.nameField)?.setText(session.name)
        view?.findViewById<EditText>(R.id.usernameField)?.setText("N/A")
        view?.findViewById<EditText>(R.id.numberField)?.setText(session.phoneNumber)
        view?.findViewById<EditText>(R.id.emailField)?.setText(session.email)
        view?.findViewById<EditText>(R.id.descriptionField)?.setText(session.description)

        // Caso Tivessemos imagem
        // Exemplo: Picasso.get().load(session.profilePicture).into(profileImageView)
    }
}
