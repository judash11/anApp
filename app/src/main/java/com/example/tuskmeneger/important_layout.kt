package com.example.tuskmeneger

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tuskmeneger.dialogs.DateMenu
import com.example.tuskmeneger.saportclassis.LayoutBasicEl

class important_layout : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_important_layout)


        val arrow = findViewById<ImageView>(R.id.imageView28)
        LayoutBasicEl(this,arrow).setupArrowListener()



        val container = findViewById<FrameLayout>(R.id.frameContainerImportant)

        val FabButton = findViewById<ImageView>(R.id.imageFABButtonMDLayout4)
        FabButton.setOnClickListener {
            println("fab")
            fabClickListener(FabButton,container,savedInstanceState)
        }

    }
    fun fabClickListener(view: ImageView, viewContainer: FrameLayout, savedInstanceState: Bundle?){
        view.visibility = View.GONE

        viewContainer.visibility = View.VISIBLE

        if (savedInstanceState == null) {
            val dateMenu = DateMenu.newInstance(R.id.imageFABButtonMDLayout4,R.id.CalendarFrameImportant)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainerImportant, dateMenu)
                .commit()


        }
    }
}