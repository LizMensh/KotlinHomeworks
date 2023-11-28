package com.example.homework_2.MVVM.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.MVVM.model.Response
import com.example.homework_2.R


class MainAdapter(
    private val items: ArrayList<Response.Item> = arrayListOf()
): ListAdapter<Response.Item, FirstHolder>(DifferentItemCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return FirstHolder(view)
    }

    override fun onBindViewHolder(holder: FirstHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItems(items: List<Response.Item>)
    {
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

class DifferentItemCallback: DiffUtil.ItemCallback<Response.Item>()
{
    override fun areItemsTheSame(oldItem: Response.Item, newItem: Response.Item): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Response.Item, newItem: Response.Item): Boolean =
        oldItem == newItem
}