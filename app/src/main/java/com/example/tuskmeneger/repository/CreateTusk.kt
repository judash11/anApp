package com.example.tuskmeneger.repository

import com.example.tuskmeneger.core.TuskEntity
import java.time.LocalDateTime

class CreateTusk(
    val createTime: LocalDateTime,
    val completeTime: LocalDateTime,
    val reminder: ReminderTusk,
    val repetition: Repetition,
    val condition: Condition,
    val importance: ImportanceTusk,
    val body: String
){
    fun Create() {
        val tuskEntity = TuskEntity(
            createTime = createTime,
            completeTime = completeTime,
            reminder = reminder,
            repetition = repetition,
            condition = condition,
            importance = importance,
            body = body
        )

//    val bd = Room.databaseBuilder<AppDataBase>(Application(), "TuskDB")
//        .setDriver(AndroidSQLiteDriver())
//        .build()
//    }
//
//    val bdOnEntity = bd


    }
}