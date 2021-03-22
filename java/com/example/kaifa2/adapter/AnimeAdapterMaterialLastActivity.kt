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


class AnimeAdapterMaterialLastActivity(val context: Context, val animeList: List<Anime>) : RecyclerView.Adapter<AnimeAdapterMaterialLastActivity.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val animeImage2: ImageView = view.findViewById(R.id.acount2)
        val animeName: TextView = view.findViewById(R.id.comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false)
        val viewHolder2=ViewHolder(view)
//        val
        viewHolder2.itemView.setOnClickListener {
            val position=viewHolder2.adapterPosition
            val anime=animeList[position]
//            for (i in animeList){
//
//            }
            Toast.makeText(parent.context,"进入 ${anime.name}板块", Toast.LENGTH_SHORT).show()
        }
        return viewHolder2
    }

    override fun onBindViewHolder(holder2:ViewHolder, position: Int) {
        val fruit = animeList[position]
        holder2.animeName.text = fruit.name
        Glide.with(context).load(fruit.ImageId).into(holder2.animeImage2);
    }
    override fun getItemCount() = animeList.size
}


