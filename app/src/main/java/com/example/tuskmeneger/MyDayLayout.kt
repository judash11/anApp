package com.example.tuskmeneger

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewParent
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter

import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tuskmeneger.dialogs.DateMenu
import com.example.tuskmeneger.saportclassis.TimeGetter

import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters

import java.util.Locale

class MyDayLayout : AppCompatActivity(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_day_layout)

        val calendar = findViewById<TextView>(R.id.frameLayoutText)
        val date = LocalDate.now()
        val day = date.dayOfMonth
        val dayOfWeek = date.dayOfWeek.getDisplayName(
            java.time.format.TextStyle.FULL,
            Locale("ru")
        )
        val month = date.month.getDisplayName(
            java.time.format.TextStyle.FULL,
            Locale("ru")
        )
        val year = date.year
//        val editText1 = findViewById<EditText>(R.id.editText1)
        calendar.setText("$dayOfWeek $day $month $year")
        val frameCalendar = findViewById<FrameLayout>(R.id.CalendarFrame)

        val cal = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.OnDateSetListener{_,year,month,dayOfMonth ->
            cal.set(Calendar.YEAR,year)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
//            println(dateFormat(cal.time))
            SetDateManeger(cal)
        }


        val everyDayBut = findViewById<ConstraintLayout>(R.id.frameLayoutEveryDay)
        everyDayBut.setOnClickListener {

        }
        val everyWorkDayBut = findViewById<ConstraintLayout>(R.id.frameLayoutEveryWorkDay)
        everyWorkDayBut.setOnClickListener {

        }

        val everyWeekBut = findViewById<ConstraintLayout>(R.id.frameLayoutEveryWeek)
        everyWeekBut.setOnClickListener {

        }

        val everyMonthBut = findViewById<ConstraintLayout>(R.id.frameLayoutEveryMonth)
        everyMonthBut.setOnClickListener {

        }

        val everyYearBut = findViewById<ConstraintLayout>(R.id.frameLayoutEveryYear)
        everyYearBut.setOnClickListener {

        }

        val satingEvery = findViewById<ConstraintLayout>(R.id.satingEvery)
        satingEvery.setOnClickListener {

        }

//        satingEvery.setOnClickListener {
//            RepetitionClickListner(
//                everyDayBut,
//                everyWorkDayBut,
//                everyWeekBut,
//                everyMonthBut,
//                everyYearBut,
//                satingEvery)
//        }

//        val satingEveryFrame = findViewById<FrameLayout>(R.id.satingEveryFrame)
//
//        val listWeek = findViewById<Spinner>(R.id.listWeekDays)






        val arrow = findViewById<ImageView>(R.id.imageView2)
        arrow.setOnClickListener {
                val Intent = Intent(this, MainActivity::class.java)
                startActivity(Intent)
                overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
        }

        val container = findViewById<FrameLayout>(R.id.frameContainer)


        val FabButton = findViewById<ImageView>(R.id.imageFABButtonMDLayout)
        FabButton.setOnClickListener {
            println("fab")
            fabClickListener(FabButton,container,savedInstanceState)
        }


//        val tasksButton = findViewById<FrameLayout>(R.id.tusksButton)
//        tasksButton.setOnClickListener {
//            TaskClickListner()
//        }

        val setDateBut = findViewById<FrameLayout>(R.id.setdateframe)
        val nextWeekBut = findViewById<FrameLayout>(R.id.nextweekframe)
        val nextDayBut = findViewById<FrameLayout>(R.id.nextdayframe)
        val toDayBut = findViewById<FrameLayout>(R.id.todayframe)
//        val calendarButton = findViewById<FrameLayout>(R.id.calendarButton)
//        calendarButton.setOnClickListener {
//            CalendarClickListner(frameCalendar,
//                datePickerDialog,
//                setDateBut,
//                nextDayBut,
//                nextWeekBut,
//                toDayBut)
//        }


        val localTime = LocalDateTime.now()
        val thisDayTimeBut = findViewById<FrameLayout>(R.id.thisDayFrame)
        val nextDayTimeFrame = findViewById<FrameLayout>(R.id.nextdayTimeframe)
        val nextWeekTimeBut = findViewById<FrameLayout>(R.id.nextweekTimeframe)
        val setDateTimeBut = findViewById<FrameLayout>(R.id.setdateTimeframe)
        val timeFrame = findViewById<FrameLayout>(R.id.TimerFrame)
//        val notificationButton = findViewById<FrameLayout>(R.id.notificationButton)
//        notificationButton.setOnClickListener {
//            NotificationClickListner(timeFrame,
//                                      datePickerDialog,
//                          thisDayTimeBut,
//                                     nextDayTimeFrame,
//                                     nextWeekTimeBut,
//                                     setDateTimeBut,
//                                                 localTime)
//        }

        val repetitionsMenu = findViewById<FrameLayout>(R.id.repetitionMenu)
//        val repetitionButton = findViewById<FrameLayout>(R.id.repetitionButton)
//        repetitionButton.setOnClickListener {
//            RepetitionClickListner(everyDayBut,
//                                   everyWorkDayBut,
//                                   everyWeekBut,
//                                   everyMonthBut,
//                                   everyYearBut,
//                                   satingEvery,
//                                   repetitionsMenu
//                                   )
//        }

//        val arrowButton2 = findViewById<ImageView>(R.id.arrowButton2)
//        arrowButton2.setOnClickListener {
//            ArrowClickListner(editText1,container,FabButton)
//        }


    }

//    fun ArrowClickListner(view: EditText, viewContainer: FrameLayout,viewFAB: ImageView){
//        println("arr")
//        val text: String = view.text.toString()
//        if (text.isNotBlank()){
//            viewContainer.visibility = View.GONE
//            viewFAB.visibility = View.VISIBLE
//            HideKeyboard(view)
//        }
//    }

    fun RepetitionClickListner(everyDayBut: ConstraintLayout,
                               everyWorkDayBut: ConstraintLayout,
                               everyWeekBut: ConstraintLayout,
                               everyMonthBut: ConstraintLayout,
                               everyYearBut: ConstraintLayout,
                               satingEveryBut: ConstraintLayout,
                               main: FrameLayout) {
        println("repit")
        main.visibility = View.VISIBLE
    }
//        val LOG = Toast.makeText(this,"every", Toast.LENGTH_SHORT)
//        val dialog =  SatingsDialog()
//        dialog.create(this)
//                               satingEveryFrame: FrameLayout,
//                               list: Spinner){
//       everyDayBut.setOnClickListener {
//            //сюда вставить повторение уведомлений
//           Toast.makeText(this,"every", Toast.LENGTH_SHORT)
//           main.visibility = View.GONE
//
//       }
//
//       everyWorkDayBut.setOnClickListener {
//
//           main.visibility = View.GONE
//       }
//
//       everyWeekBut.setOnClickListener {
//
//           main.visibility = View.GONE
//       }
//
//       everyMonthBut.setOnClickListener {
//
//           main.visibility = View.GONE
//       }
//
//       everyYearBut.setOnClickListener {
//
//           main.visibility = View.GONE
//       }
//
//       satingEveryBut.setOnClickListener {
//
//           main.visibility = View.GONE
//
//          val dialog =  SatingsDialog()
//           val dayTimeGetter = dialog.create(this){ TimeGetter ->
//           val logger = Toast.makeText(this, "gg", Toast.LENGTH_SHORT)
//               if (TimeGetter != null){
//                   val logger = Toast.makeText(this, "gg", Toast.LENGTH_SHORT)
//                   logger.show()
//               }else{
//                   val logger = Toast.makeText(this, "gg", Toast.LENGTH_SHORT)
//                   logger.show()
//               }
//           }
////          val gg = dayTimeGetter.getWeekDay().toString()
////           logger.show()
//
//       }
//
//
//
//    private fun getSatingMenu(Frame: FrameLayout,
//                              spinner: Spinner,
//                              Pn: ConstraintLayout,
//                              VT: ConstraintLayout,
//                              Sr: ConstraintLayout,
//                              Ht: ConstraintLayout,
//                              Pt: ConstraintLayout,
//                              Sb: ConstraintLayout,
//                              Vs: ConstraintLayout,
//                              positive){
//        getList(spinner)
//        Frame.visibility = View.VISIBLE
//
//    }
//
//    private fun getList(spinner: Spinner){
//        val weekDays = arrayOf("Пн","Вт","Ср","Чт","Пт","Сб","Вс")
//        val adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_spinner_item,
//            weekDays
//        )
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = adapter
//
////        spinner.onItemClickListener = object : AdapterView.OnItemSelectedListener{
////            override fun onItemSelected(
////                parent: AdapterView<*>?,
////                view: View?,
////                posi
////            ) {
////
////            }
////        }
//
//
//    }

    fun fabClickListener(view: ImageView, viewContainer: FrameLayout, savedInstanceState: Bundle?){
        view.visibility = View.GONE
//        val Container = findViewById<FrameLayout>(R.id.frameContainer)
        viewContainer.visibility = View.VISIBLE
//        DateMenu(this).create()
        if (savedInstanceState == null) {
            val dateMenu = DateMenu()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainer, dateMenu)
                .commit()

//            ShowKeyboard(dateMenu.getEditText())
        }
    }

//    fun TaskClickListner(){
//
//    }

    fun CalendarClickListner(view: FrameLayout,
                             datePickerDialog: DatePickerDialog.OnDateSetListener,
                             toDayBut: FrameLayout,
                             nextDayBut: FrameLayout,
                             nextWeekBut: FrameLayout,
                             setDateBut: FrameLayout
                             ){
        view.visibility = View.VISIBLE


        toDayBut.setOnClickListener {
            val cal = Calendar.getInstance()
            ToDayClickListner(view,cal)
        }
        nextDayBut.setOnClickListener {
            val cal = Calendar.getInstance()
            NextDayClickListner(view,cal)
        }
        nextWeekBut.setOnClickListener {
            val cal = Calendar.getInstance()
            NextWeekListner(view,cal)
        }
        setDateBut.setOnClickListener {
            val cal = Calendar.getInstance()
            SetDateListner(view,datePickerDialog,cal)
        }



    }

    fun NotificationClickListner(view: FrameLayout,
                                 datePickerDialog: DatePickerDialog.OnDateSetListener,
                                 toDayBut: FrameLayout,
                                 nextDayBut: FrameLayout,
                                 nextWeekBut: FrameLayout,
                                 setDateBut: FrameLayout,
                                 date: LocalDateTime){
        view.visibility = View.VISIBLE
        toDayBut.setOnClickListener {
            ToDayTimeListner(view,date)
        }
        nextDayBut.setOnClickListener {
            NextDayTimeListner(view, date)
        }
        nextWeekBut.setOnClickListener {
            NextWeekTimeListner(view, date)
        }
        setDateBut.setOnClickListener {
            SetDateTimeListner(view,datePickerDialog, date)
        }



    }


    fun ToDayTimeListner(view: FrameLayout,date: LocalDateTime){
        view.visibility = View.GONE
        val datePlusHours = date.plusHours(3)
        val dateTruncate = datePlusHours.truncatedTo(ChronoUnit.HOURS)
        println(dateTruncate.hour.toString()+" "+dateTruncate.minute.toString())
    }
    fun NextDayTimeListner(view: FrameLayout,date: LocalDateTime){
        view.visibility = View.GONE
        val datePlusDay = date.plusDays(1)
        val dateTruncate = datePlusDay.truncatedTo(ChronoUnit.HOURS)
        println(dateTruncate.hour.toString()+" "+dateTruncate.dayOfMonth.toString())
    }
    fun NextWeekTimeListner(view: FrameLayout,dateTime: LocalDateTime){
        view.visibility = View.GONE
        val date: LocalDate = dateTime.toLocalDate()
        val datePlusWeek = date.plusWeeks(1)
        val dateTruncate = datePlusWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        println(dateTruncate.dayOfMonth.toString()+" "+dateTruncate.dayOfWeek.toString())
    }
    fun SetDateTimeListner(view: FrameLayout,dateDialog: DatePickerDialog.OnDateSetListener,date: LocalDateTime){
        view.visibility = View.GONE
        val cal = Calendar.getInstance()
        getCalendarDialog(dateDialog,cal)

    }














//    fun ShowKeyboard(edit: EditText){
//        edit.requestFocus()
//        edit.post{
//            val imm = edit.context.getSystemService(Context.INPUT_METHOD_SERVICE)
//            as InputMethodManager
//               imm.showSoftInput(edit, InputMethodManager.SHOW_IMPLICIT)
//        }
//    }
//
//    fun HideKeyboard(edit: EditText){
//        val imm =edit.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(edit.windowToken, 0)
//    }

    fun ToDayClickListner(main: FrameLayout,cal: Calendar){
        main.visibility = View.GONE
        SetDateManeger(cal)
    }

    fun NextDayClickListner(main: FrameLayout,cal: Calendar){
        main.visibility = View.GONE
        cal.add(Calendar.DAY_OF_MONTH,1)
        SetDateManeger(cal)
    }

    fun NextWeekListner(main: FrameLayout,cal: Calendar){
        main.visibility = View.GONE
        val current = cal.get(Calendar.DAY_OF_WEEK)
        var daysUntilMonday = (Calendar.MONDAY - current + 7) % 7
        if (daysUntilMonday == 0) daysUntilMonday = 7
        cal.add(Calendar.DAY_OF_WEEK,daysUntilMonday)
        SetDateManeger(cal)
    }

    fun SetDateListner(main: FrameLayout,datePicker: DatePickerDialog.OnDateSetListener,cal: Calendar){
        main.visibility = View.GONE
        getCalendarDialog(datePicker,cal)

    }

    fun dateFormat(time: Any): String? {
        val format1 = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(time)
        return format1
    }

    fun getCalendarDialog(dateDialog: DatePickerDialog.OnDateSetListener,calendar: Calendar) {
        DatePickerDialog(
            this,
            dateDialog,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)

        ).show()

    }

    fun SetDateManeger(cal: Calendar){
        println(dateFormat(cal.time))
    }

}
