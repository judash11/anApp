package com.example.tuskmeneger.repository

import java.time.LocalDateTime

data class TaskObject(
    val createTime: LocalDateTime,
    val completeTime: LocalDateTime,
    val reminder: ReminderTask,
    val repetition: Repetition,
    val condition: Condition,
    val importance: ImportanceTask,
    val body: String
){}