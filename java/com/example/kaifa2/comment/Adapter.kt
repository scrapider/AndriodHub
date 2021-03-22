package com.example.kaifa2.comment


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kaifa2.R
import com.example.kaifa2.dataBase.LoginDataBaseHelper


class Adapter(val context: Context, val animeList: List<Comment>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val account: TextView = view.findViewById(R.id.acount2)
        val comment: TextView = view.findViewById(R.id.comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false)
        val viewHolder2=ViewHolder(view)
//        val
        viewHolder2.itemView.setOnClickListener {
            val position=viewHolder2.adapterPosition
            val anime=animeList[position]
        }
        return viewHolder2
    }

    override fun onBindViewHolder(holder2:ViewHolder, position: Int) {
        val fruit = animeList[position]
        holder2.account.text =fruit.account
        holder2.comment.text=fruit.comment

    }



    override fun getItemCount() = animeList.size
}


