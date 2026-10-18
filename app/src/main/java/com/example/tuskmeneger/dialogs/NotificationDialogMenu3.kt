package com.example.tuskmeneger.dialogs

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.example.tuskmeneger.R
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.util.Locale

class NotificationDialogMenu3 : Fragment(R.layout.dialog_date_menu3) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localTime = LocalDateTime.now()
        val thisDayButton = view.findViewById<FrameLayout>(R.id.thisDayFrame)
        thisDayButton.setOnClickListener { _ -> toDayTimeListener(localTime)

        }
        val nextDayTimeButton = view.findViewById<FrameLayout>(R.id.nextdayTimeframe)
        nextDayTimeButton.setOnClickListener { _ -> nextDayTimeListener(localTime)

        }
        val nextWeekTimeButton = view.findViewById<FrameLayout>(R.id.nextweekTimeframe)
        nextWeekTimeButton.setOnClickListener { _ -> nextWeekTimeListener(localTime)

        }
        val setDateTimeButton = view.findViewById<FrameLayout>(R.id.setdateTimeframe)
        setDateTimeButton.setOnClickListener { _ -> setDateTimeListener(localTime)

        }




    }
    private fun toDayTimeListener(date: LocalDateTime){

        val datePlusHours = date.plusHours(3)
        val dateTruncate = datePlusHours.truncatedTo(ChronoUnit.HOURS)
        println(dateTruncate.hour.toString()+" "+dateTruncate.minute.toString())
        close()
    }
    private fun nextDayTimeListener(date: LocalDateTime){

        val datePlusDay = date.plusDays(1)
        val dateTruncate = datePlusDay.truncatedTo(ChronoUnit.HOURS)
        println(dateTruncate.hour.toString()+" "+dateTruncate.dayOfMonth.toString())
        close()
    }
    private fun nextWeekTimeListener(dateTime: LocalDateTime){

        val date: LocalDate = dateTime.toLocalDate()
        val datePlusWeek = date.plusWeeks(1)
        val dateTruncate = datePlusWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        println(dateTruncate.dayOfMonth.toString()+" "+dateTruncate.dayOfWeek.toString())
        close()
    }

    private fun setDateTimeListener(date: LocalDateTime){
        val cal = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.OnDateSetListener{_,year,month,dayOfMonth ->
            cal.set(Calendar.YEAR,year)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            setDateManager(cal)
        }

        getCalendarDialog(datePickerDialog,cal)

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
    private fun setDateManager(cal: Calendar){
        println(dateFormat(cal.time))
    }
    private fun close(){
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }

}