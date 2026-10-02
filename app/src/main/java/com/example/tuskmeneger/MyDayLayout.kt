package com.example.tuskmeneger

import android.content.Context
import android.content.Intent
import android.icu.text.Edits
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.time.LocalDate
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
        val editText1 = findViewById<EditText>(R.id.editText1)
        calendar.setText("$dayOfWeek $day $month $year")
        val frameCalendar = findViewById<FrameLayout>(R.id.CalendarFrame)







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
            fabClickListener(FabButton,container,editText1)
        }


        val tasksButton = findViewById<FrameLayout>(R.id.tusksButton)
        tasksButton.setOnClickListener {
            TaskClickListner()
        }

        val calendarButton = findViewById<FrameLayout>(R.id.calendarButton)
        calendarButton.setOnClickListener {
            CalendarClickListner(frameCalendar)
        }

        val notificationButton = findViewById<FrameLayout>(R.id.notificationButton)
        notificationButton.setOnClickListener {
            NotificationClickListner()
        }

        val repetitionButton = findViewById<FrameLayout>(R.id.repetitionButton)
        repetitionButton.setOnClickListener {
            RepetitionClickListner()
        }

        val arrowButton2 = findViewById<ImageView>(R.id.arrowButton2)
        arrowButton2.setOnClickListener {
            ArrowClickListner(editText1,container,FabButton)
        }


    }

    fun ArrowClickListner(view: EditText, viewContainer: FrameLayout,viewFAB: ImageView){
        println("arr")
        val text: String = view.text.toString()
        if (text.isNotBlank()){
            viewContainer.visibility = View.GONE
            viewFAB.visibility = View.VISIBLE
            HideKeyboard(view)
        }
    }

    fun RepetitionClickListner(){

    }

    fun fabClickListener(view: ImageView, viewContainer: FrameLayout, viewEdit: EditText){
        view.visibility = View.GONE
//        val Container = findViewById<FrameLayout>(R.id.frameContainer)
        viewContainer.visibility = View.VISIBLE
        ShowKeyboard(viewEdit)

    }

    fun TaskClickListner(){

    }

    fun CalendarClickListner(view: FrameLayout){
        view.visibility = View.VISIBLE


        val toDayBut = findViewById<FrameLayout>(R.id.todayframe)
        toDayBut.setOnClickListener {
            ToDayClickListner()
        }
        val nextDayBut = findViewById<FrameLayout>(R.id.nextdayframe)
        nextDayBut.setOnClickListener {
            NextDayClickListner()
        }
        val nextWeekBut = findViewById<FrameLayout>(R.id.nextweekframe)
        nextWeekBut.setOnClickListener {
            NextWeekListner()
        }
        val setDateBut = findViewById<FrameLayout>(R.id.setdateframe)
        setDateBut.setOnClickListener {
            SetDateListner()
        }



    }

    fun NotificationClickListner(){

    }

    fun ShowKeyboard(edit: EditText){
        edit.requestFocus()
        edit.post{
            val imm = edit.context.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
               imm.showSoftInput(edit, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun HideKeyboard(edit: EditText){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edit.windowToken, 0)
    }

    fun ToDayClickListner( butView: FrameLayout){

    }

    fun NextDayClickListner(butView: FrameLayout){

    }

    fun NextWeekListner(butView: FrameLayout){

    }

    fun SetDateListner(butView: FrameLayout){

    }
}
