package com.example.tuskmeneger.dialogs

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

import com.example.tuskmeneger.R
import com.example.tuskmeneger.logic.Task
import com.example.tuskmeneger.repository.Repetition
import com.example.tuskmeneger.saportclassis.TimeGetter
import java.time.DayOfWeek

open class RepetitionDialogMenu4(
    var builder: Task.Builder
) : Fragment(R.layout.dialog_date_menu4) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val everyDayButton = view.findViewById<ConstraintLayout>(R.id.frameLayoutEveryDay)
        everyDayButton.setOnClickListener { _ -> everyDayListener()
        }

        val workDayButton = view.findViewById<ConstraintLayout>(R.id.frameLayoutEveryWorkDay)
        workDayButton.setOnClickListener { _ -> everyWorkDayListener()

        }

        val everyWeekButton = view.findViewById<ConstraintLayout>(R.id.frameLayoutEveryWeek)
        everyWeekButton.setOnClickListener { _ -> everyWeekListener()

        }

        val everyMonthButton = view.findViewById<ConstraintLayout>(R.id.frameLayoutEveryMonth)
        everyMonthButton.setOnClickListener { _ -> everyMonthListener()

        }

        val everyYearButton = view.findViewById<ConstraintLayout>(R.id.frameLayoutEveryYear)
        everyYearButton.setOnClickListener { _ -> everyYearListener()

        }

        val everySatingButton = view.findViewById<ConstraintLayout>(R.id.satingEvery)
        everySatingButton.setOnClickListener { _ -> everySatingListener()

        }







    }

    private fun everyDayListener(){
        this.builder.setRepetition(Repetition.EveryDay)
        println(this.builder.repetition1.toString())
        close()
    }
    private fun everyWorkDayListener(){
        this.builder.setRepetition(Repetition.EveryWorkDay)
        println(this.builder.repetition1.toString())
        close()
    }
    private fun everyWeekListener(){
        this.builder.setRepetition(Repetition.EveryWeek)
        println(this.builder.repetition1.toString())
        close()
    }
    private fun everyMonthListener(){
        this.builder.setRepetition(Repetition.EveryMonth)
        println(this.builder.repetition1.toString())
        close()
    }
    private fun everyYearListener(){
        this.builder.setRepetition(Repetition.EveryYear)
        println(this.builder.repetition1.toString())
        close()
    }
    private fun everySatingListener(){
        SatingDialog().create(requireActivity(),::TimeGetterListener )
    }
    protected open fun TimeGetterListener(timeGetter: TimeGetter?){
        if (timeGetter == null){
            println("null")
        }else{
            val week = timeGetter.getWeekDay()
            val day = timeGetter.isDayEnabled()
            val month = timeGetter.isMonthEnabled()
            val year = timeGetter.isYearEnabled()

            if (week != null){
                val dayOfWeek = timeGetter.getWeekDay()
                var dayofweek: Repetition = Repetition.nullable
                when (dayOfWeek){
                    DayOfWeek.MONDAY -> dayofweek = Repetition.SatingMonday
                    DayOfWeek.TUESDAY -> dayofweek = Repetition.SatingThursday
                    DayOfWeek.WEDNESDAY -> dayofweek = Repetition.SatingWednesday
                    DayOfWeek.THURSDAY -> dayofweek = Repetition.SatingThursday
                    DayOfWeek.FRIDAY -> dayofweek = Repetition.SatingFriday
                    DayOfWeek.SATURDAY -> dayofweek = Repetition.SatingSaturday
                    DayOfWeek.SUNDAY -> dayofweek = Repetition.SatingSunday
                    else -> println("error")
                }
                this.builder.setRepetition(dayofweek)
            }
            else if (day){
                this.builder.setRepetition(Repetition.EveryDay)
            }
            else if(month){
                this.builder.setRepetition(Repetition.EveryMonth)
            }
            else if(year){
                this.builder.setRepetition(Repetition.EveryYear)
            }


            println(this.builder.repetition1.toString())
        }
    }

    open fun close(){
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }

}