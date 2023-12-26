package com.example.homework_2.MVVM.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_2.MVVM.model.Response
import com.example.homework_2.MainActivity
import com.example.homework_2.R


class FirstHolder(view: View): RecyclerView.ViewHolder(view)
{
    private val context = view.context
    private val card = view.findViewById<CardView>(R.id.item_view__cv)
    private val image = view.findViewById<ImageView>(R.id.item_image)
    private val title = view.findViewById<TextView>(R.id.item_title)
    private val description = view.findViewById<TextView>(R.id.item_description)

    fun bind(item: Response.Item)
    {
        title.text = item.title
        description.text = item.description

        Glide
            .with(context)
            .load(item.images.parameters.url)
            .placeholder(R.drawable.ic_download)
            .error(R.drawable.ic_error)
            .centerCrop()
            .into(image)
    }
}