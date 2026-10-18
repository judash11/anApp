package com.example.tuskmeneger.repository

import java.time.LocalDateTime

data class TuskObject(
    val createTime: LocalDateTime,
    val completeTime: LocalDateTime,
    val reminder: ReminderTusk,
    val repetition: Repetition,
    val condition: Condition,
    val importance: ImportanceTusk,
    val body: String
){}