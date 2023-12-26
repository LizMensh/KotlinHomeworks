package com.example.homework_2.MVVM.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_2.CountAddImages
import com.example.homework_2.MVVM.model.Response
import com.example.homework_2.MVVM.viewmodel.MainViewModel
import com.example.homework_2.MVVM.viewmodel.MenuViewModel
import com.example.homework_2.R
import com.example.homework_2.StatusLoad
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MenuFragment : Fragment(R.layout.fragment_menu)
{
    private lateinit var viewModel: MenuViewModel

    private val question: String = "GOJO"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity(), SavedStateViewModelFactory())[MenuViewModel::class.java]

        if (viewModel.item.value == null) {
            viewModel.getNewItem(question)
        }

        setArticle(viewModel.item.value)

        viewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                StatusLoad.ERROR -> {
                    view.findViewById<ContentLoadingProgressBar>(R.id.menu_loadingbar)
                        .visibility = View.INVISIBLE

                    val text = "Error: Internet"
                    val duration = Toast.LENGTH_SHORT

                    Toast.makeText(this.context, text, duration).show()
                }

                StatusLoad.LOADING -> {
                    view.findViewById<ContentLoadingProgressBar>(R.id.menu_loadingbar)
                        .visibility = View.VISIBLE
                }

                StatusLoad.SUCCESS -> {
                    view.findViewById<ContentLoadingProgressBar>(R.id.menu_loadingbar)
                        .visibility = View.INVISIBLE

                    setArticle(viewModel.item.value)
                }

                else -> {

                }
            }
        }

        view.findViewById<MaterialCardView>(R.id.menu_button_skip).setOnClickListener {
            viewModel.getNewItem(question)
        }
    }

    private fun setArticle(item: Response.Item?) {
        item?.let {
            view?.apply {
                val image = findViewById<ImageView>(R.id.menu_image)
                Glide
                    .with(context)
                    .load(it.images.parameters.url)
                    .placeholder(R.drawable.ic_download)
                    .error(R.drawable.ic_error)
                    .centerCrop()
                    .into(image)

                findViewById<TextView>(R.id.menu_title).apply {
                    text = it.title
                }

                findViewById<TextView>(R.id.menu_description).apply {
                    text = it.description
                }
            }
        }
    }
}