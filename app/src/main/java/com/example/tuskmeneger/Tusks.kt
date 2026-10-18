package com.example.tuskmeneger

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tuskmeneger.saportclassis.LayoutBasicEl

class Tusks(): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tusk_s)

        val arrow = findViewById<ImageView>(R.id.imageView2222)
        LayoutBasicEl(this,arrow).setupArrowListener()

    }
}