package com.example.tuskmeneger.dialogs


import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout

import androidx.fragment.app.Fragment
import com.example.tuskmeneger.R
import java.text.SimpleDateFormat
import java.util.Locale

class CalendarDialogMenu2(
//    val context: Context,
    val container: FrameLayout
): Fragment(R.layout.dialog_date_menu2) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cal = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.OnDateSetListener{_,year,month,dayOfMonth ->
            cal.set(Calendar.YEAR,year)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            setDateManager(cal)
        }

        val toDayButton = view.findViewById<FrameLayout>(R.id.todayframe)
        toDayButton.setOnClickListener { _ ->
            val cal = Calendar.getInstance()
            toDayClickListener(cal)

        }

        val nextDayButton = view.findViewById<FrameLayout>(R.id.nextdayframe)
        nextDayButton.setOnClickListener { _ ->
            val cal = Calendar.getInstance()
            nextDayClickListener(cal)

        }

        val nextWeekButton = view.findViewById<FrameLayout>(R.id.nextweekframe)
        nextWeekButton.setOnClickListener { _ ->
            val cal = Calendar.getInstance()
            nextWeekListener(cal)

        }

        val setDateButton = view.findViewById<FrameLayout>(R.id.setdateframe)
        setDateButton.setOnClickListener { _ ->
            val cal = Calendar.getInstance()
            setDateListener(datePickerDialog,cal)

        }

    }
    private fun toDayClickListener(cal: Calendar){
        setDateManager(cal)
        close()
    }

    private fun nextDayClickListener(cal: Calendar){
        cal.add(Calendar.DAY_OF_MONTH,1)
        setDateManager(cal)
        close()
    }

    private fun nextWeekListener(cal: Calendar){
        val current = cal.get(Calendar.DAY_OF_WEEK)
        var daysUntilMonday = (Calendar.MONDAY - current + 7) % 7
        if (daysUntilMonday == 0) daysUntilMonday = 7
        cal.add(Calendar.DAY_OF_WEEK,daysUntilMonday)
        setDateManager(cal)
        close()
    }

    private fun setDateListener(datePicker: DatePickerDialog.OnDateSetListener,cal: Calendar){
        getCalendarDialog(datePicker,cal)

    }

    private fun dateFormat(time: Any): String? {
        val format1 = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(time)
        return format1
    }

    private fun getCalendarDialog(dateDialog: DatePickerDialog.OnDateSetListener,calendar: Calendar) {
        val dialog = DatePickerDialog(
            requireActivity(),
            dateDialog,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)

        )
        dialog.setOnShowListener {
            val positiveButton = dialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener { _ ->
                dialog.dismiss()
                close()
            }

            val negativeButton = dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
            negativeButton.setOnClickListener { _ ->
                dialog.dismiss()
            }

        }
        dialog.show()

    }
    private fun close(){
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }
    private fun setDateManager(cal: Calendar){
        println(dateFormat(cal.time))
    }
}