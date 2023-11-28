package com.example.homework_2.utils

import android.app.Application

class MainApplication : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        Connector.initialize(this)
    }

}