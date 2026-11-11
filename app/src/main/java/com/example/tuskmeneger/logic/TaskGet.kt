package com.example.tuskmeneger.logic

import com.example.tuskmeneger.core.GetColumn
import com.example.tuskmeneger.core.TaskEntity
import com.example.tuskmeneger.repository.Condition
import com.example.tuskmeneger.repository.ImportanceTask
import com.example.tuskmeneger.repository.Repetition
import java.time.LocalDateTime

class TaskGet {

    suspend fun Get() = GetColumn().GetAll()


    fun toTask(
         name: String,
         createTime: LocalDateTime,
         completeTime: LocalDateTime,
         reminder: LocalDateTime,
         repetition1: Repetition,
         condition: Condition,
         importance: ImportanceTask,
         body: String
    ): Task{
        val builder = Task.Builder()
        builder.setName(name)
        builder.setCreateTime(createTime)
        builder.setBody(body)
        builder.setRepetition(repetition1)
        builder.setImportance(importance)
        builder.setCondition(condition)
        builder.setReminder(reminder)
        builder.setCompleteTime(completeTime)
        return builder.Build()
    }

}