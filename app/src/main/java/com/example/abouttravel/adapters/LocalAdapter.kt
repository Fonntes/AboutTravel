package com.example.abouttravel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.abouttravel.R
import com.example.abouttravel.data.entities.Local
import com.example.abouttravel.data.entities.Media
import com.example.abouttravel.data.entities.UserLocalRatings
import com.squareup.picasso.Picasso

class LocalAdapter(
    private val locals: List<Local>,
    private val media: List<Media>,
    private val ratings: List<UserLocalRatings>
) : RecyclerView.Adapter<LocalAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.places_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val local = locals[position]
        val localMedia = media.find { it.locationId == local.id }
        val localRating = ratings.find { it.localId == local.id }

        holder.localLabel.text = local.label
        holder.rating.text = localRating?.rating?.toString() ?: "No rating"
        localMedia?.let {
            Picasso.get().load(it.path).into(holder.image)
        }
    }

    override fun getItemCount(): Int {
        return locals.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val localLabel: TextView = itemView.findViewById(R.id.text)
        val rating: TextView = itemView.findViewById(R.id.stars)
        val image: ImageView = itemView.findViewById(R.id.image)
    }
}
