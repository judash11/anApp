package com.example.tuskmeneger.saportclassis


import java.time.DayOfWeek

class TimeGetter(
    val week: DayOfWeek?,
    val month: Boolean,
    val year: Boolean,
    val day: Boolean
) {
    public fun getWeekDay(): DayOfWeek?{
        return week
    }
    public fun isMonthEnabled(): Boolean{
        return month
    }
    public fun isYearEnabled(): Boolean{
        return year
    }
    public fun isDayEnabled(): Boolean{
        return day
    }
}