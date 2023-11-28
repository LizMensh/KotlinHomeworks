package com.example.rk1
import android.graphics.Color

data class Square(
    val position: Int,
    var backgroundColor: Int = if (position % 2 == 0) R.color.red else R.color.blue,
    val textColor: Int = if (position % 2 == 0) Color.BLACK else Color.WHITE
)
