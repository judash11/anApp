package com.example.tuskmeneger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tuskmeneger.dialogs.DateMenu
import com.example.tuskmeneger.logic.Task
import com.example.tuskmeneger.repository.ImportanceTask
import com.example.tuskmeneger.repository.TaskViewModel
import com.example.tuskmeneger.saportclassis.ActivI
import com.example.tuskmeneger.saportclassis.LayoutBasicEl
import com.example.tuskmeneger.saportclassis.WGetData
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.getValue

class important_layout : AppCompatActivity(), ActivI {

    private val viewModel: TaskViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_important_layout)

        val arrow = findViewById<ImageView>(R.id.imageView28)
        LayoutBasicEl().setupArrowListener(this,arrow)



        val container = findViewById<FrameLayout>(R.id.frameContainerImportant)

        val FabButton = findViewById<ImageView>(R.id.imageFABButtonMDLayout4)
        FabButton.setOnClickListener {
            println("fab")
            fabClickListener(FabButton,container,savedInstanceState)
        }
        Scroller()

    }



    fun fabClickListener(view: ImageView, viewContainer: FrameLayout, savedInstanceState: Bundle?){
        view.visibility = View.GONE

        viewContainer.visibility = View.VISIBLE

        if (savedInstanceState == null) {
            val dateMenu = DateMenu.newInstance(R.id.imageFABButtonMDLayout4,R.id.CalendarFrameImportant,
                ImportanceTask.Hard)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainerImportant, dateMenu)
                .commit()


        }
        Scroller()
    }


    override fun Scroller(){




        val scroll = findViewById<LinearLayout>(R.id.layoutLinear)
//            val linearParam = LinearLayout.LayoutParams(
//                675,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//
//            )
//            linearParam.leftMargin = 70
//            linearParam.topMargin = 10



        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                LayoutBasicEl().Scroller(this@important_layout.viewModel,
                   this@important_layout,
                    this@important_layout,
                    scroll,
                    R.id.main2,
                    WGetData.Hard)
            }
        }
    }
    override fun onRestart() {
        super.onRestart()
        findViewById<LinearLayout>(R.id.linearScrollMyDay).removeAllViews()
//        this.thread.interrupt()

    }
}