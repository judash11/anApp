package com.example.tuskmeneger.core

import com.example.tuskmeneger.logic.Task

class EntityCreateColumn (
    val task: Task
){
    private val bd = AppDataBase.GetInstance(getContextApp.appContext)
    private val taskBD = bd.TaskDao()
      suspend fun Create() {

                  val taskEntity = CreateTaskEntity(null)

                  taskBD.SetTask(taskEntity)
                  val task = taskBD.GetAllTask()
                  print(task.toString())

      }
    fun CreateTaskEntity(id: Long?) = TaskEntity(
        id = id,
        name = this.task.name,
        createTime = this.task.createTime,
        completeTime = this.task.completeTime,
        reminder = this.task.reminder,
        repetition = this.task.repetition1,
        condition = this.task.condition,
        importance = this.task.importance,
        body = this.task.body

    )
//      suspend fun GetAll(): List<TaskEntity>{
//          return taskBD.GetAllTask()
//      }
}