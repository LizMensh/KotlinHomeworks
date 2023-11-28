package com.example.homework_2.MVVM.view

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_2.MVVM.model.Response
import com.example.homework_2.R


class FirstHolder(view: View): RecyclerView.ViewHolder(view)
{
    private val context = view.context
    private val card = view.findViewById<CardView>(R.id.item_view__cv)
    private val image = view.findViewById<ImageView>(R.id.item_view__iv)

    fun bind(item: Response.Item)
    {
        Glide
            .with(context)
            .load(item.images.parameters.url)
            .placeholder(R.drawable.ic_download)
            .error(R.drawable.ic_error)
            .centerCrop()
            .into(image)

        image.setOnClickListener{
            Glide
                .with(context)
                .load(item.images.parameters.url)
                .placeholder(R.drawable.ic_download)
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(image)
        }
    }
}