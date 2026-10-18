package com.example.tuskmeneger

import android.content.Intent

import android.os.Build
import android.os.Bundle
import android.view.View

import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tuskmeneger.dialogs.DateMenu
import com.example.tuskmeneger.saportclassis.LayoutBasicEl

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

        calendar.setText("$dayOfWeek $day $month $year")

//        val mainBg = findViewById<ConstraintLayout>(R.id.mainconstrate1)
//        val hasFragments = supportFragmentManager.fragments.isNotEmpty()
//        if (hasFragments) {
//            mainBg.isClickable = true
//        }

        val arrow = findViewById<ImageView>(R.id.imageView2)
        LayoutBasicEl(this,arrow).setupArrowListener()







        val container = findViewById<FrameLayout>(R.id.frameContainer)


        val FabButton = findViewById<ImageView>(R.id.imageFABButtonMDLayout)
        FabButton.setOnClickListener {
            println("fab")
            fabClickListener(FabButton,container,savedInstanceState)
        }




     }


    fun fabClickListener(view: ImageView, viewContainer: FrameLayout, savedInstanceState: Bundle?){
        view.visibility = View.GONE

        viewContainer.visibility = View.VISIBLE

        if (savedInstanceState == null) {
            val dateMenu = DateMenu.newInstance(R.id.imageFABButtonMDLayout,R.id.CalendarFrame)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainer, dateMenu)
                .commit()


        }
    }



//R.id.CalendarFrame



}
