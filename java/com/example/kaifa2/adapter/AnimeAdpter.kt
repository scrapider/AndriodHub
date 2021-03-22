package com.example.kaifa2.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.kaifa2.datatype.Anime
import com.example.kaifa2.R

class AnimeAdpter(val animelist: List<Anime>) : RecyclerView.Adapter<AnimeAdpter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val animeImage: ImageView = view.findViewById(R.id.acount2)
        val animeNmae: TextView = view.findViewById(R.id.comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        val viewHolder=ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position=viewHolder.adapterPosition
            val anime=animelist[position]
            Toast.makeText(parent.context,"you clicked view ${anime.name}", Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val anime = animelist[position]
        holder.animeImage.setImageResource(anime.ImageId)
        holder.animeNmae.text = anime.name
    }

    override fun getItemCount(): Int {
        return animelist.size
    }
}