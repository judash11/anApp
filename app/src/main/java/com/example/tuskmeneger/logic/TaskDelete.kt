package com.example.tuskmeneger.logic

import com.example.tuskmeneger.core.GetColumn
import com.example.tuskmeneger.core.TaskEntity

class TaskDelete
{
    suspend fun deleteTask(task: TaskEntity){
        GetColumn().delete(task)
    }
    suspend fun deleteAllTask(){
        GetColumn().deleteAll()
    }
}