package com.example.tuskmeneger.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuskmeneger.core.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TuskViewModel(app : Application) : AndroidViewModel(app) {
    private val db = AppDataBase.GetInstance(app).TaskDao()


    fun createTusk(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                RepositoryBD().cr()
            }
        }
    }
}