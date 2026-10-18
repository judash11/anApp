package com.example.tuskmeneger

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tuskmeneger.dialogs.DateMenu
import com.example.tuskmeneger.saportclassis.LayoutBasicEl
import com.google.android.material.card.MaterialCardView

class planned_layout : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_planned_layout)

        val arrow = findViewById<ImageView>(R.id.imageView23)
        LayoutBasicEl(this,arrow).setupArrowListener()


        val fab = findViewById<ImageView>(R.id.imageFABButtonMDLayout3)
        val container = findViewById<FrameLayout>(R.id.frameContainerPlanned)
        fab.setOnClickListener { _ -> fabClickListener(fab,container, savedInstanceState) }



        val planedd = findViewById<MaterialCardView>(R.id.constraintLayout101)
        val but = findViewById<MaterialCardView>(R.id.materialCardViewBut)
        but.setOnClickListener {
            if (planedd.visibility == View.VISIBLE) {
                planedd.visibility = View.GONE
            }else {
                planedd.visibility = View.VISIBLE
            }
        }



        val explain = findViewById<ConstraintLayout>(R.id.expired)
        explain.setOnClickListener {
            planedd.visibility = View.GONE
            explain()
        }
        val toDayConstraint = findViewById<ConstraintLayout>(R.id.ToDayConstraint)
        toDayConstraint.setOnClickListener {
            planedd.visibility = View.GONE
            toDay()
        }
        val nextDayConstraints = findViewById<ConstraintLayout>(R.id.nextDayConstraint)
        nextDayConstraints.setOnClickListener {
            planedd.visibility = View.GONE
            nextDay()
        }
        val thisWeekConstraint = findViewById<ConstraintLayout>(R.id.ThisWeekConstraint)
        thisWeekConstraint.setOnClickListener {
            planedd.visibility = View.GONE
            thisWeek()
        }
        val laterConstraint = findViewById<ConstraintLayout>(R.id.LaterConstraint)
        laterConstraint.setOnClickListener {
            planedd.visibility = View.GONE
            later()
        }
        val allThosePlanned = findViewById<ConstraintLayout>(R.id.AllThosePlanned)
        allThosePlanned.setOnClickListener {
            planedd.visibility = View.GONE
            planned()
        }

    }


    fun explain(){

    }
    fun toDay(){

    }
    fun nextDay(){

    }
    fun thisWeek(){

    }
    fun later(){

    }
    fun planned(){

    }


    fun fabClickListener(view: ImageView, viewContainer: FrameLayout, savedInstanceState: Bundle?){
        view.visibility = View.GONE

        viewContainer.visibility = View.VISIBLE

        if (savedInstanceState == null) {
            val dateMenu = DateMenu.newInstance(R.id.imageFABButtonMDLayout3,R.id.CalendarFramePlanned)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainerPlanned, dateMenu)
                .commit()


        }
    }
}