package com.example.tuskmeneger.core

import androidx.room3.ColumnTypeConverter
import com.example.tuskmeneger.repository.Condition
import com.example.tuskmeneger.repository.ImportanceTask
import com.example.tuskmeneger.repository.ReminderTask
import com.example.tuskmeneger.repository.Repetition
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {

    private val format = DateTimeFormatter.ISO_DATE_TIME

    @ColumnTypeConverter
    fun fromCreateTime(createTime: LocalDateTime?): String? =
        createTime?.format(format)


    @ColumnTypeConverter
    fun toCreateTime(time: String?): LocalDateTime? =
        time?.let { LocalDateTime.parse(it, format) }


//    @ColumnTypeConverter
//    fun fromCompleteTime(completeTime: LocalDateTime?): String? =
//        completeTime?.format(format)
//
//
//    @ColumnTypeConverter
//    fun toCompleteTime(completeTime: String?) =
//        completeTime?.let { LocalDateTime.parse(it,format) }


    @ColumnTypeConverter
    fun fromReminder(reminder: ReminderTask?): String? =
        reminder?.toString()


    @ColumnTypeConverter
    fun toReminder(reminder: String?): ReminderTask? =
        reminder?.let { ReminderTask.valueOf(it) }


    @ColumnTypeConverter
    fun fromRepetition(repetition: Repetition?): String? =
        repetition?.toString()


    @ColumnTypeConverter
    fun toRepetition(repetition: String?): Repetition? =
        repetition?.let { Repetition.valueOf(it) }


    @ColumnTypeConverter
    fun fromCondition(condition : Condition?): String? =
        condition?.toString()


    @ColumnTypeConverter
    fun toCondition(condition: String?): Condition? =
        condition?.let { Condition.valueOf(it) }


    @ColumnTypeConverter
    fun fromImportance(importance: ImportanceTask?): String? =
        importance?.toString()


    @ColumnTypeConverter
    fun toImportance(importance: String?): ImportanceTask? =
        importance?.let { ImportanceTask.valueOf(it) }


//    @ColumnTypeConverter
//    fun fromBody(body: String?): String? =
//        body
//
//
//    @ColumnTypeConverter
//    fun toBody(body: String?): String? =
//        body

}