package com.example.abouttravel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.abouttravel.R
import com.example.abouttravel.data.entities.Trip
import com.squareup.picasso.Picasso

class TravelAdapter(
    private val travels: List<Trip>
) : RecyclerView.Adapter<TravelAdapter.MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.travels_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val travel = travels[position]
        holder.travel.text = travel.title
        holder.places.text = travel.country //Alterar
        Picasso.get().load(travel.image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return travels.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val travel: TextView = itemView.findViewById(R.id.travel)
        val places: TextView = itemView.findViewById(R.id.places)
        val image: ImageView = itemView.findViewById(R.id.image)
    }



}