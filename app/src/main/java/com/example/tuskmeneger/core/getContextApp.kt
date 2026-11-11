package com.example.tuskmeneger.core

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance

class getContextApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance: getContextApp
            private set

        val appContext: Context get() = instance.applicationContext
    }
}