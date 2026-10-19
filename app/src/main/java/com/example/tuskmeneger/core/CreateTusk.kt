package com.example.tuskmeneger.core

import android.app.Application
import com.example.tuskmeneger.repository.Condition
import com.example.tuskmeneger.repository.ImportanceTask
import com.example.tuskmeneger.repository.ReminderTask
import com.example.tuskmeneger.repository.Repetition
import java.time.LocalDateTime

class CreateTask(
    val createTime: LocalDateTime,
    val completeTime: LocalDateTime,
    val reminder: ReminderTask,
    val repetition: Repetition,
    val condition: Condition,
    val importance: ImportanceTask,
    val body: String
){
    suspend fun Create() {
        val taskEntity = TaskEntity(
            createTime = createTime,
            completeTime = completeTime,
            reminder = reminder,
            repetition = repetition,
            condition = condition,
            importance = importance,
            body = body
        )

        val bd = AppDataBase.GetInstance(Application())
        val taskBD = bd.TaskDao()
        taskBD.SetTask(taskEntity)
        val task = taskBD.GetAllTask()
        print(task.toString())

    }
}