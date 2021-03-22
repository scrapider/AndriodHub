package com.example.kaifa2.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kaifa2.R
import com.example.kaifa2.activity.Animation2
import com.example.kaifa2.activity.guimie.GuiVideo
import com.example.kaifa2.activity.sao.SaoVideo1
import com.example.kaifa2.dataBase.DataBase
import com.example.kaifa2.datatype.Anime


class AnimeAdapterMaterial(val context: Context, val animeList: List<Anime>) : RecyclerView.Adapter<AnimeAdapterMaterial.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val animeImage: ImageView = view.findViewById(R.id.acount2)
        val animeName: TextView = view.findViewById(R.id.comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false)
        val viewHolder=ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val position=viewHolder.adapterPosition
            val anime=animeList[position]
            if (position==0){
                val intent=Intent(parent.context, DataBase::class.java)
                context.startActivity(intent)
            }
            if (position==1){
                val intent=Intent(parent.context, Animation2::class.java)
                context.startActivity(intent)
            }
            if (position==2){
                val intent=Intent(parent.context, SaoVideo1::class.java)
                context.startActivity(intent)
            }
            if (position==3){
                val intent=Intent(parent.context, GuiVideo::class.java)
                context.startActivity(intent)
            }
            Toast.makeText(parent.context,"进入 ${anime.name}板块", Toast.LENGTH_SHORT).show()
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