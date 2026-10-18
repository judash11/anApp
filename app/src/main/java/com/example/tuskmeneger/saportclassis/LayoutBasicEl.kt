package com.example.tuskmeneger.saportclassis

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tuskmeneger.MainActivity

class LayoutBasicEl(
    private val activity: AppCompatActivity,
    private val arrow: ImageView
) {
    fun setupArrowListener() {
        arrow.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java) // intent с маленькой буквы

            activity.startActivity(intent)

            activity.overridePendingTransition(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
        }
    }
}