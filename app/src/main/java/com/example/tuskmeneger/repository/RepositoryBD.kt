package com.example.tuskmeneger.repository

import com.example.tuskmeneger.core.CreateTask
import java.time.LocalDateTime

class RepositoryBD {
    suspend fun cr(){
        CreateTask(
            LocalDateTime.now(),
            LocalDateTime.now(),
            ReminderTask.NextWeek,
            Repetition.EveryWorkDay,
            Condition.InProgress,
            ImportanceTask.Easy,
            "asdasd"
        ).Create()
    }
}