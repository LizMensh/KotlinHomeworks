package com.example.rk1

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var frame: CoordinatorLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        frame = findViewById(R.id.frame)
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)

        adapter = MainAdapter()

        recyclerView.adapter = adapter
        for (i in 1..9)
            adapter.items.add(Square(i))

        val columnCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 3

        recyclerView.layoutManager = GridLayoutManager(this, columnCount)
        savedInstanceState?.let {
            for (i in 10..it.getInt("size")){
                adapter.items.add(Square(i))
            }
        }

        fab.setOnClickListener {
            adapter.items.add(Square(adapter.items.size + 1))
            adapter.notifyItemInserted(adapter.items.size - 1)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("size", adapter.itemCount)
    }
}