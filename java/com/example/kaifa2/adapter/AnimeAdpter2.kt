package com.example.kaifa2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kaifa2.R
import com.example.kaifa2.datatype.Anime

class AnimeAdpter2(val context: Context, val animeList: List<Anime>) : RecyclerView.Adapter<AnimeAdpter2.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val animeImage: ImageView = view.findViewById(R.id.acount2)
        val animeName: TextView = view.findViewById(R.id.comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false)
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val anime = animeList[position]
            Toast.makeText(parent.context,"你点击了 ${anime.name}", Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = animeList[position]
        holder.animeName.text = fruit.name
        Glide.with(context).load(fruit.ImageId).into(holder.animeImage);
    }

    override fun getItemCount() = animeList.size
}

