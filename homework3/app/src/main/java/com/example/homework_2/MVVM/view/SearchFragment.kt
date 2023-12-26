package com.example.homework_2.MVVM.view

import android.opengl.Visibility
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.core.widget.doBeforeTextChanged
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.CountAddImages
import com.example.homework_2.MVVM.viewmodel.MainViewModel
import com.example.homework_2.R
import com.example.homework_2.StatusLoad
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SearchFragment : Fragment(R.layout.fragment_search)
{
    private lateinit var mainViewModel: MainViewModel

    private val mainAdapter = MainAdapter()

    private var question = "GOJO"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity(), SavedStateViewModelFactory())[MainViewModel::class.java]

        view.findViewById<RecyclerView>(R.id.search_rv).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }

        savedInstanceState?.getString("query")?.apply {
            question = this
            view.findViewById<SearchView>(R.id.search_input).setQuery(question, false)
        }

        mainViewModel.items.value?.apply {
            if (isEmpty())
                mainViewModel.addItems(CountAddImages, question)
            else
                mainAdapter.refreshItems(this)
        } ?: mainViewModel.addItems(CountAddImages, question)

        mainViewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                StatusLoad.ERROR -> {
                    view.findViewById<ContentLoadingProgressBar>(R.id.search_loadingbar)
                        .visibility = View.INVISIBLE

                    val text = "Error: Internet"
                    val duration = Toast.LENGTH_SHORT

                    Toast.makeText(this.context, text, duration).show()
                }

                StatusLoad.LOADING -> {
                    view.findViewById<ContentLoadingProgressBar>(R.id.search_loadingbar)
                        .visibility = View.VISIBLE
                }

                StatusLoad.SUCCESS -> {
                    view.findViewById<ContentLoadingProgressBar>(R.id.search_loadingbar)
                        .visibility = View.INVISIBLE

                    mainViewModel.items.value?.apply {
                        mainAdapter.refreshItems(this)
                    }
                }

                else -> { }
            }
        }

        view.findViewById<SearchView>(R.id.search_input).apply {
            setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(p0: String?): Boolean {
                    mainViewModel.items.value?.clear()
                    question = query.toString()
                    mainViewModel.addItems(CountAddImages, question)
                    return false
                }
            })
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("query", question)
    }
}