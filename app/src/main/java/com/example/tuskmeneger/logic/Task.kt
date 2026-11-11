package com.example.tuskmeneger.logic

import android.os.Parcelable
import com.example.tuskmeneger.repository.Condition
import com.example.tuskmeneger.repository.ImportanceTask
import com.example.tuskmeneger.repository.Repetition
import java.time.LocalDateTime




class Task private constructor(
    val name: String,
    val createTime: LocalDateTime,
    val completeTime: LocalDateTime,
    val reminder: LocalDateTime,
    val repetition1: Repetition,
    val condition: Condition,
    val importance: ImportanceTask,
    val body: String
) {

    data class Builder(
        var name: String = "",
        var createTime: LocalDateTime = LocalDateTime.now(),
        var completeTime: LocalDateTime = LocalDateTime.now(),
        var reminder: LocalDateTime = LocalDateTime.now(),
        var repetition1: Repetition = Repetition.nullable,
        var condition: Condition = Condition.InProgress,
        var importance: ImportanceTask = ImportanceTask.Easy,
        var body: String = ""
    ){
        fun setName(name: String) = apply { this.name = name }
        fun setCreateTime(time: LocalDateTime) = apply { this.createTime = time }
        fun setCompleteTime(time: LocalDateTime) = apply { this.completeTime = time }
        fun setReminder(remind: LocalDateTime) = apply { this.reminder = remind }
        fun setRepetition(repetition: Repetition) = apply { this.repetition1 = repetition }
        fun setCondition(condition_: Condition) = apply { this.condition = condition_ }
        fun setImportance(importance_: ImportanceTask) = apply { this.importance = importance_ }
        fun setBody(body_: String) = apply { this.body = body_ }
        fun Build() = Task(name, createTime,completeTime,reminder,repetition1,condition,importance,body)

    }


}