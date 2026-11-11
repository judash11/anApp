package com.example.tuskmeneger.dialogs

import android.app.DatePickerDialog
import android.content.Context
import java.util.Calendar
import android.os.Bundle
import android.os.Messenger
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.example.tuskmeneger.R
import androidx.fragment.app.Fragment
import com.example.tuskmeneger.logic.Task
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.util.Date
import java.util.Locale
import java.util.TimeZone

open class NotificationDialogMenu3(
    var builder: Task.Builder
) : Fragment(R.layout.dialog_date_menu3) {

    private var cal: Calendar = Calendar.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val localTime = LocalDateTime.now()
        val thisDayButton = view.findViewById<FrameLayout>(R.id.thisDayFrame)
        thisDayButton.setOnClickListener { _ ->
            if(localTime
                .plusHours(3L)
                .toLocalDate() != localTime
                                  .plusDays(1L)
                                  .toLocalDate()
                ) {
                toDayTimeListener(localTime)
            }else{
                Toast.makeText(
                    this.context,
                    "сегодня позже недоступно",
                    Toast.LENGTH_SHORT
                ).show()
            }
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
        this.builder.setReminder(dateTruncate)
        println(this.builder.reminder.toString())
        close()
    }
    private fun nextDayTimeListener(date: LocalDateTime){

        val datePlusDay = date.plusDays(1)
        val dateTruncate = datePlusDay.truncatedTo(ChronoUnit.HOURS)
        println(dateTruncate.hour.toString()+" "+dateTruncate.dayOfMonth.toString())
        this.builder.setReminder(dateTruncate)
        println(this.builder.reminder.toString())
        close()
    }
    private fun nextWeekTimeListener(dateTime: LocalDateTime){

        val date: LocalDate = dateTime.toLocalDate()
        val datePlusWeek = date.plusWeeks(1)
        val dateTruncate = datePlusWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        println(dateTruncate.dayOfMonth.toString()+" "+dateTruncate.dayOfWeek.toString())

        this.builder.setReminder(dateTruncate.atTime(0,0))
        println(this.builder.reminder.toString())
        close()
    }

    private fun setDateTimeListener(date: LocalDateTime){
        val cal = Calendar.getInstance().apply {
            time = Date.from(date.atZone(ZoneId.systemDefault()).toInstant())
        }
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
                val picker = dialog.datePicker
                dateDialog.onDateSet(picker, picker.year, picker.month, picker.dayOfMonth)
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
        Log.d("NotificationDialogMenu3",dateFormat(cal.time).toString())
        this.cal = cal
        this.builder.setReminder(getTaskObject())
        close()
    }
    protected open fun close(){
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }

    private fun getTaskObject(): LocalDateTime = LocalDateTime.ofInstant(
        this.cal.toInstant(),
        ZoneId.systemDefault()
    )

}