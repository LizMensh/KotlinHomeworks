package com.example.homework_2.MVVM.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.CountAddImages
import com.example.homework_2.MVVM.viewmodel.MainViewModel
import com.example.homework_2.R
import com.example.homework_2.StatusLoad
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment(R.layout.fragment_main)
{
    private lateinit var mainViewModel: MainViewModel

    private val mainAdapter = MainAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity(), SavedStateViewModelFactory())[MainViewModel::class.java]

        view.findViewById<RecyclerView>(R.id.fragment_main__rv).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }

        mainViewModel.addItems(CountAddImages)

        view.findViewById<FloatingActionButton>(R.id.fragment_main__fab).setOnClickListener {
            mainViewModel.addItems(CountAddImages)
        }

        mainViewModel.receivedItems.observe(viewLifecycleOwner) {
            if (it != null) {
                mainAdapter.addItems(it)
            }
        }

        mainViewModel.status.observe(viewLifecycleOwner) {
            if (it == StatusLoad.ERROR)
            {
                val text = "Error: Internet"
                val duration = Toast.LENGTH_SHORT

                Toast.makeText(this.context, text, duration).show()
            }
        }
    }
}