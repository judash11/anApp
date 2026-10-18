package com.example.tuskmeneger

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tuskmeneger.saportclassis.LayoutBasicEl


class assigned_to_me_layout : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_assigned_to_me_layout)

        val arrow = findViewById<ImageView>(R.id.imageView303)
        LayoutBasicEl(this, arrow).setupArrowListener()

    }


}