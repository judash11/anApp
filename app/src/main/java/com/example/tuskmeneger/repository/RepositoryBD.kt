package com.example.tuskmeneger.repository

import android.util.Log
import com.example.tuskmeneger.core.EntityCreateColumn
import com.example.tuskmeneger.core.GetColumn
import com.example.tuskmeneger.core.TaskEntity
import com.example.tuskmeneger.logic.Task
import com.example.tuskmeneger.logic.TaskDelete
import com.example.tuskmeneger.logic.TaskGet

class RepositoryBD {

    suspend fun Build(task: Task){
        EntityCreateColumn(
            task
            ).Create()
    }

    suspend fun GetAll(): List<Task> = TaskGet().Get().map { TaskEntity -> TaskGet().toTask(
        TaskEntity.name,
        TaskEntity.createTime,
        TaskEntity.completeTime,
        TaskEntity.reminder,
        TaskEntity.repetition,
        TaskEntity.condition,
        TaskEntity.importance,
        TaskEntity.body
    )
    }
    suspend fun deleteTask(index: Int){
        var list: List<TaskEntity> = TaskGet().Get()
        if  (index != 0) {
            if (index < list.size) {
                val task = list[index]
                TaskDelete().deleteTask(task)
            }
        }
        else{
            TaskDelete().deleteAllTask()
        }


    }
    suspend fun update(index: Int,task: Task){
        val list: List<TaskEntity> = TaskGet().Get()
        val listElementId = list.get(index).id
        val taskEntity = EntityCreateColumn(task).CreateTaskEntity(listElementId)
        Log.d("Repository","update")



        GetColumn().update(taskEntity)
    }

    suspend fun getTaskInDao(index: Int): Task{
        val taskEntity = GetColumn().getTaskInDao(index)
        return TaskGet().toTask(
            name = taskEntity.name,
            createTime = taskEntity.createTime,
            condition = taskEntity.condition,
            completeTime = taskEntity.completeTime,
            body = taskEntity.body,
            reminder = taskEntity.reminder,
            repetition1 = taskEntity.repetition,
            importance = taskEntity.importance
        )
    }



}