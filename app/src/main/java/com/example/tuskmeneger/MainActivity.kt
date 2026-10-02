package com.example.tuskmeneger

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import org.w3c.dom.Entity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Locale


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myDay_button = findViewById<FrameLayout>(R.id.frameLayout1)
        myDay_button.setOnClickListener {
                myDayButton()
            }

        val critical_button = findViewById<FrameLayout>(R.id.frameLayout2)
        critical_button.setOnClickListener {
                criticalButton()
            }

        val planned_button = findViewById<FrameLayout>(R.id.frameLayout3)
        planned_button.setOnClickListener {
                plannedButton()
            }

        val appointed_button = findViewById<FrameLayout>(R.id.frameLayout4)
        appointed_button.setOnClickListener {
                appointedButton()
            }

        val tasks_button = findViewById<FrameLayout>(R.id.frameLayout5)
        tasks_button.setOnClickListener {
                tasksButton()
            }

        val plusButton = findViewById<FrameLayout>(R.id.plusButton)
        plusButton.setOnClickListener {
            plusButton()
        }

    }



    fun myDayButton(){
        println("myDayButton")
        val intent = Intent(this, MyDayLayout::class.java)
        startActivity(intent)


//        val textView = TextView(this)
//        textView.setText("sdfsdf")
//        scrollLiner(textView)
    }

    fun criticalButton(){
        println("criticalButton")
    }

    fun plannedButton(){
        println("plannedButton")
    }

    fun appointedButton(){
        println("appointedButton")
    }

    fun tasksButton(){
        println("tasksButton")
    }

    fun scrollLiner(textView: TextView) {
          val scroll_LinerLayout = findViewById<LinearLayout>(R.id.scrollLine1)



        // 1. FrameLayout как корневой контейнер
        val frameLayout = FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }
        frameLayout.isClickable = true

        // 2. ConstraintLayout внутри FrameLayout


//        val constraint = ConstraintLayout.generateViewId()




        val constraintLayout = ConstraintLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            setPadding(-10, 22, 16, 16)
            id = View.generateViewId()
        }

        // 3. TextView (слева)
        val text = textView


        val textParams = ConstraintLayout.LayoutParams(
            0, // width = 0dp (растягивается)
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            startToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            // привяжем к картинке после её создания (см. ниже)
        }

        // 4. ImageView (справа)
        val imageView = ImageView(this).apply {
            setImageResource(R.drawable.dicks)
            id = View.generateViewId()
        }


        val imageParams = ConstraintLayout.LayoutParams(
            210,   // width
            46
        ).apply{
            topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            startToStart = ConstraintLayout.LayoutParams.PARENT_ID

        }

        // Теперь связываем текст с картинкой: текст заканчивается там, где начинается картинка
        textParams.startToEnd = imageView.id

        // 5. Собираем иерархию
        constraintLayout.addView(text, textParams)
        constraintLayout.addView(imageView, imageParams)
        frameLayout.addView(constraintLayout)

        
        scroll_LinerLayout.addView(frameLayout)
    }

    fun plusButton(){
        val input = EditText(this).apply {
            hint = "название"
        }

        val dialog = AlertDialog.Builder(this)
            .setTitle("Новый список")
            .setView(input)
            .setIcon(R.drawable.resource_true)
//            .setMessage("Файл не сохранён")
            .setPositiveButton("ОК") { dialog, _ -> dialog.dismiss() }
            .setNegativeButton("Отмена", null)
            .create()

        dialog.setOnShowListener {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#181717")))

        }
        dialog.show()
    }

}
