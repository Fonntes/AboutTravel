package com.example.abouttravel.pages.menus

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import com.example.abouttravel.R
import com.example.abouttravel.helpers.Network

class NetworkChanger : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            val isConnected = Network(context).isNetworkAvailable()
            // Obtém o FragmentManager da sua Activity
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager

            if (!isConnected) {
                // Exibe o fragmento se não houver conexão
                fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, NoWifiFragment())
                    .commit()
            } else {
                // Remove o fragmento se a conexão for restabelecida
                val fragment = fragmentManager.findFragmentById(R.id.fragmentContainerView)
                if (fragment is NoWifiFragment) {
                    fragmentManager.beginTransaction()
                        .remove(fragment)
                        .commit()
                }
            }
        }
    }
}