package com.example.rk1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter() :
    RecyclerView.Adapter<MainViewHolder>() {

    val items: ArrayList<Square> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.square, null)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class MainViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.square)


    fun bind(item: Square) {
        name.text = item.position.toString()
        name.setBackgroundResource(item.backgroundColor)
        name.setTextColor(item.textColor)
    }

}