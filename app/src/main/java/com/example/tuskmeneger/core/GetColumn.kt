package com.example.tuskmeneger.core

class GetColumn {

    private val bd = AppDataBase.GetInstance(getContextApp.appContext)
    private val taskBD = bd.TaskDao()

    suspend fun GetAll(): List<TaskEntity>{
        return taskBD.GetAllTask()
    }
    suspend fun delete(task: TaskEntity){
        return taskBD.deleteTask(task)
    }
    suspend fun deleteAll(){
        return taskBD.clearAllTasks()
    }
    suspend fun update(task: TaskEntity){
        return taskBD.updateTask(task)
    }
    suspend fun getTaskInDao(index: Int): TaskEntity{
        return taskBD.getTaskInDao(index)
    }
}