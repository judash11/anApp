package com.example.tuskmeneger.saportclassis

import android.util.Log
import com.example.tuskmeneger.logic.Task
import com.example.tuskmeneger.repository.ReminderTask
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjusters

class Converter {

    public fun toReminder(reminder : LocalDateTime): ReminderTask {


        Log.d("Converter",reminder.toString())
        val reminderToDate = reminder.toLocalDate()
        val reminderNextWeek = LocalDate
            .now()
            .with (
                TemporalAdjusters
                    .next(DayOfWeek.MONDAY)
            )
        val reminderNextDay = LocalDate
            .now()
            .plusDays(1)


        if(reminderToDate == LocalDate.now()){
           return ReminderTask.Today
        }
        else if(reminderToDate == reminderNextDay){
            return ReminderTask.Tomorrow
        }
        else if(reminderToDate == reminderNextWeek){
            return ReminderTask.NextWeek
        }
        else{
            return ReminderTask.SetDate
        }

    }
    public fun toBuilder(task: Task): Task.Builder{
        val builder = Task.Builder().apply {
            setName(task.name)
            setBody(task.body)
            setCondition(task.condition)
            setCompleteTime(task.completeTime)
            setImportance(task.importance)
            setReminder(task.reminder)
            setCreateTime(task.createTime)
            setRepetition(task.repetition1)

        }

        return builder
    }

    public fun toComplete(complete: LocalDateTime): String{
        val CompleteToDate = complete.toLocalDate()
        val CompleteNextWeek = LocalDate
            .now()
            .with (
                TemporalAdjusters
                    .next(DayOfWeek.MONDAY)
            )
        val CompleteNextDay = LocalDate
            .now()
            .plusDays(1)


        if(CompleteToDate == LocalDate.now()){
            return "сегодня"
        }
        else if(CompleteToDate == CompleteNextDay){
            return "завтра"
        }
        else if(CompleteToDate == CompleteNextWeek){
            return "на следующей неделе"
        }
        else{
            return CompleteToDate.toString()
        }
    }



}