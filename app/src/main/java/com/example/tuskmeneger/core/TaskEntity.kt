package com.example.tuskmeneger.core


import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.example.tuskmeneger.repository.Condition
import com.example.tuskmeneger.repository.ImportanceTask
import com.example.tuskmeneger.repository.ReminderTask
import com.example.tuskmeneger.repository.Repetition
import java.time.LocalDateTime

@Entity(tableName = "task")
data class TaskEntity(

    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val createTime: LocalDateTime,
    val completeTime: LocalDateTime,
    val reminder: ReminderTask,
    val repetition: Repetition,
    val condition: Condition,
    val importance: ImportanceTask,
    val body: String
)
