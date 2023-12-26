package com.example.homework_2

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.homework_2.MVVM.view.MenuFragment
import com.example.homework_2.MVVM.view.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        val searchFragment = SearchFragment()
        val menuFragment = MenuFragment()

        findViewById<BottomNavigationView>(R.id.bottomNavigationView).apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.search -> setCurrentFragment(searchFragment)
                    R.id.menu -> setCurrentFragment(menuFragment)
                    else -> {}
                }

                true
            }

            savedInstanceState?.getInt("number")?.let {
                selectedItemId = it
            } ?: run {
                selectedItemId = menu[0].itemId
            }

        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.flFragment, fragment)
            commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState.putInt("number", findViewById<BottomNavigationView>(R.id.bottomNavigationView).selectedItemId)
    }
}