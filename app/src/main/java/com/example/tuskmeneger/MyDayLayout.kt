package com.example.tuskmeneger



import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tuskmeneger.core.getContextApp

import com.example.tuskmeneger.dialogs.DateMenu
import com.example.tuskmeneger.logic.Task
import com.example.tuskmeneger.repository.ImportanceTask

import com.example.tuskmeneger.repository.TaskViewModel
import com.example.tuskmeneger.saportclassis.ActivI
import com.example.tuskmeneger.saportclassis.Converter
import com.example.tuskmeneger.saportclassis.LayoutBasicEl
import com.example.tuskmeneger.saportclassis.WGetData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


import java.time.LocalDate
import java.time.LocalDateTime

import java.util.Locale
import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine

class MyDayLayout : AppCompatActivity(), ActivI{

    private val viewModel: TaskViewModel by viewModels()



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

        val scroll = findViewById<LinearLayout>(R.id.linearScrollMyDay)
        val linearParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
//        val textParams = ConstraintLayout.LayoutParams(
//            ConstraintLayout.LayoutParams.WRAP_CONTENT,
//            ConstraintLayout.LayoutParams.WRAP_CONTENT
//        )

//        val allTasks = viewModel.getAll()

        findViewById<LinearLayout>(R.id.linearScrollMyDay).removeAllViews()
        Scroller()








//        val mainBg = findViewById<ConstraintLayout>(R.id.mainconstrate1)
//        val hasFragments = supportFragmentManager.fragments.isNotEmpty()
//        if (hasFragments) {
//            mainBg.isClickable = true
//        }

        val arrow = findViewById<ImageView>(R.id.imageView2)
        LayoutBasicEl().setupArrowListener(this,arrow)





//            lifecycleScope.launch {
//                Scroller()
//
//            }


        val container = findViewById<FrameLayout>(R.id.frameContainer)


        val FabButton = findViewById<ImageView>(R.id.imageFABButtonMDLayout)
        FabButton.setOnClickListener {
            println("fab")
            fabClickListener(FabButton,container,savedInstanceState)
        }


//        onStartScrollUpdate()

     }



    override fun Scroller(){




            val scroll = findViewById<LinearLayout>(R.id.linearScrollMyDay)
//            val linearParam = LinearLayout.LayoutParams(
//                675,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//
//            )
//            linearParam.leftMargin = 70
//            linearParam.topMargin = 10



            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {


                    LayoutBasicEl().Scroller(this@MyDayLayout.viewModel,
                                             this@MyDayLayout,
                                             this@MyDayLayout,
                                              scroll,
                                              R.id.main,
                        WGetData.Today
                    )

                    }
                }

        }



    fun fabClickListener(view: ImageView, viewContainer: FrameLayout, savedInstanceState: Bundle?){


        if (savedInstanceState == null) {
            view.visibility = View.GONE

            viewContainer.visibility = View.VISIBLE
            val dateMenu = DateMenu.newInstance(
                R.id.imageFABButtonMDLayout,
                R.id.CalendarFrame,
                ImportanceTask.Easy,
                )
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainer, dateMenu)
                .commit()


        }
        Scroller()
        val ff = viewModel.getAll().toString()
        Log.d("FAB_TAG", "fab clicked, ${ff}")

    }

    fun deleteTask(index: Int){


                viewModel.delTask(index)
                println("GG")
                Scroller()


    }




   override fun onRestart() {
        super.onRestart()
        findViewById<LinearLayout>(R.id.linearScrollMyDay).removeAllViews()
//        this.thread.interrupt()

   }





}
