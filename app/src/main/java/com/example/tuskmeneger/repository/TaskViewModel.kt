package com.example.tuskmeneger.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuskmeneger.core.AppDataBase
import com.example.tuskmeneger.core.getContextApp
import com.example.tuskmeneger.logic.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TaskViewModel(app : Application) : AndroidViewModel(app) {
//    private val db = AppDataBase.GetInstance(app).TaskDao()


    fun delTask(task: Int) =
        viewModelScope.launch {
            RepositoryBD().deleteTask(task)
        }


    fun getAll() : Flow<List<Task>> = flow{
         emit(withContext(Dispatchers.IO){ RepositoryBD().GetAll()})
    }.flowOn(Dispatchers.IO)


    fun getOne(index: Int, onResult: (Task?) -> Unit) {
        viewModelScope.launch {

            val task = RepositoryBD().getTaskInDao(index)
            onResult(task)
        }


    }

    fun updateTask(task: Task,index: Int){
        viewModelScope.launch {
            RepositoryBD().update(index, task)
        }
    }

}