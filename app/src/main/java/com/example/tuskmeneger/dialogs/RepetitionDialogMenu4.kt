package com.example.tuskmeneger.dialogs

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

import com.example.tuskmeneger.R
import com.example.tuskmeneger.saportclassis.TimeGetter

class RepetitionDialogMenu4 : Fragment(R.layout.dialog_date_menu4) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("dd")
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
        close()
    }
    private fun everyWorkDayListener(){
        close()
    }
    private fun everyWeekListener(){
        close()
    }
    private fun everyMonthListener(){
        close()
    }
    private fun everyYearListener(){
        close()
    }
    private fun everySatingListener(){
        SatingDialog().create(requireActivity(),::TimeGetterListener )
    }
    private fun TimeGetterListener(timeGetter: TimeGetter?){
        if (timeGetter == null){
            println("null")
        }else{
            val week = timeGetter.getWeekDay().toString()
            val day = timeGetter.isDayEnabled().toString()
            val month = timeGetter.isMonthEnabled().toString()
            val year = timeGetter.isYearEnabled().toString()

            println("$week $day $month $year")
        }
    }

    private fun close(){
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }

}