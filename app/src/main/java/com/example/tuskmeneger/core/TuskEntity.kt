package com.example.tuskmeneger.core

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.example.tuskmeneger.repository.Condition
import com.example.tuskmeneger.repository.ImportanceTusk
import com.example.tuskmeneger.repository.ReminderTusk
import com.example.tuskmeneger.repository.Repetition
import java.time.LocalDateTime

@Entity(tableName = "tusk")
data class TuskEntity(

    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val createTime: LocalDateTime,
    val completeTime: LocalDateTime,
    val reminder: ReminderTusk,
    val repetition: Repetition,
    val condition: Condition,
    val importance: ImportanceTusk,
    val body: String
)
