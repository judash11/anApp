package com.example.tuskmeneger.saportclassis


import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tuskmeneger.FullscreenFragmentForm
import com.example.tuskmeneger.MainActivity
import com.example.tuskmeneger.R
import com.example.tuskmeneger.logic.Task
import com.example.tuskmeneger.repository.Condition
import com.example.tuskmeneger.repository.ImportanceTask
import com.example.tuskmeneger.repository.TaskViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class LayoutBasicEl(
//    private val activity: AppCompatActivity,
//    private val arrow: ImageView
) {
    var task_: Task? = null
    fun setupArrowListener(activity: AppCompatActivity,arrow: ImageView) {
        arrow.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java) // intent с маленькой буквы

            activity.startActivity(intent)

            activity.overridePendingTransition(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )

        }
    }

    suspend fun Scroller(viewModel: TaskViewModel,
                         activity: AppCompatActivity,
                         context: Context,
                         scroll: LinearLayout,
                         constraintt: Int,
                         get: WGetData){




//        val scroll = view.findViewById<LinearLayout>(R.id.linearScrollMyDay)
        val linearParam = LinearLayout.LayoutParams(
            675,
            LinearLayout.LayoutParams.WRAP_CONTENT

        )
        linearParam.leftMargin = 70


        linearParam.topMargin = 10





                viewModel.getAll().collect { tasks ->

                    scroll.removeAllViews()
                    tasks.forEachIndexed { index, task ->
                        this.task_ = task

                        if(get == WGetData.Today){
                            if(task_!!.completeTime.toLocalDate().equals(LocalDate.now())) {
                                Scroll(
                                    context,
                                    linearParam,
                                    activity,
                                    constraintt,
                                    index,
                                    task,
                                    viewModel,
                                    scroll
                                )
                            }
                        }else if(get == WGetData.Hard){

                                if(task_!!.importance == ImportanceTask.Hard) {
                                    Scroll(
                                        context,
                                        linearParam,
                                        activity,
                                        constraintt,
                                        index,
                                        task,
                                        viewModel,
                                        scroll
                                    )
                                }
                        }
//                        if(task_!!.completeTime.toLocalDate().equals(LocalDate.now())) {
//                            val constraint = ConstraintLayout(context).apply {
//                                layoutParams = linearParam
//                                setBackgroundResource(R.drawable.background_task)
//                                isClickable = true
//                                setOnClickListener {
//
//                                 activity.supportFragmentManager.beginTransaction()
//                                        .replace(constraintt,
//                                            FullscreenFragmentForm.newInstance(
//                                                "Мой день",
//                                                index,
//                                                constraintt
//                                            )
//                                        ).commit()
//
//                                }
//                            }
//                            val text = TextView(context).apply {
//                                id = View.generateViewId()
//                                text = task_!!.body
//                                textSize = 18f
//                            }
//                            if(task_!!.completeTime < LocalDateTime.now()){
//                                val taskb = Converter().toBuilder(task_!!)
//                                taskb.setCondition(Condition.Overdue)
//                                task_ = taskb.Build()
//                            }
//                            if (task.condition == Condition.Сompleted) {
//                                text.paintFlags = text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//                            }
//
//
//                            val starSelector = ImageView(context).apply {
//                                id = View.generateViewId()
//                                setImageResource(R.drawable.star_selector)
//                                isClickable = true
//                                if(task.importance == ImportanceTask.Easy){
//                                    isSelected = false
//                                }else if(task.importance == ImportanceTask.Hard){
//                                    isSelected = true
//                                }
//                                setOnClickListener {
//                                    Log.d("STAR_TAG", "star clicked, task=${task.importance}")
//                                    isSelected = !isSelected
//                                    val builder = Task.Builder().apply {
//                                        setBody(task_!!.body)
//                                        setCondition(task_!!.condition)
//                                        setCompleteTime(task_!!.completeTime)
//                                        setCreateTime(task_!!.createTime)
//                                        setRepetition(task_!!.repetition1)
//                                        setReminder(task_!!.reminder)
//                                    }
//                                    if (isSelected){
//                                        builder.setImportance(ImportanceTask.Hard)
//                                    }else{
//                                        builder.setImportance(ImportanceTask.Easy)
//
//                                    }
//
//                                    viewModel.updateTask(builder.Build(),index)
//
//                                }
//                            }
//
//                            constraint.addView(text)
//                            constraint.addView(starSelector)
//
//                            val constraintSet = ConstraintSet()
//                            constraintSet.clone(constraint)
//                            constraintSet.connect(
//                                text.id,
//                                ConstraintSet.START,
//                                ConstraintSet.PARENT_ID,
//                                ConstraintSet.START,
//                                180
//                            )
//                            constraintSet.connect(
//                                text.id,
//                                ConstraintSet.TOP,
//                                ConstraintSet.PARENT_ID,
//                                ConstraintSet.TOP,
//                                0
//                            )
//                            constraintSet.connect(
//                                starSelector.id,
//                                ConstraintSet.START,
//                                ConstraintSet.PARENT_ID,
//                                ConstraintSet.START,
//                                20
//                            )
//                            constraintSet.connect(
//                                starSelector.id,
//                                ConstraintSet.TOP,
//                                ConstraintSet.PARENT_ID,
//                                ConstraintSet.TOP,
//                                0
//                            )
//                            constraintSet.applyTo(constraint)
//
//
//
//                            constraint.background = R.color.black.toDrawable()
//
//
//
//                            constraint.layoutParams = linearParam
//                            constraint.setBackgroundResource(R.drawable.background_task)
//                            scroll.addView(constraint)
//                        }
                    }
                }
            }

    fun Scroll(context: Context,
               linearParam: LinearLayout.LayoutParams,
               activity: AppCompatActivity,
               constraintt: Int,
               index: Int,
               task: Task,
               viewModel: TaskViewModel,
               scroll: LinearLayout){
        val constraint = ConstraintLayout(context).apply {
            layoutParams = linearParam
            setBackgroundResource(R.drawable.background_task)
            isClickable = true
            setOnClickListener {

                activity.supportFragmentManager.beginTransaction()
                    .replace(constraintt,
                        FullscreenFragmentForm.newInstance(
                            "Мой день",
                            index,
                            constraintt
                        )
                    ).commit()
//                                    visibility = View.GONE
//                                    if (index < tasks.size) {
//                                        deleteTask(index)
//                                        println(task.body)
//                                    }
            }
        }
        val text = TextView(context).apply {
            id = View.generateViewId()
            text = task.name
            textSize = 18f
        }
        if(task_!!.completeTime < LocalDateTime.now()){
            val taskb = Converter().toBuilder(task_!!)
            taskb.setCondition(Condition.Overdue)
            task_ = taskb.Build()
        }
        if (task_!!.condition == Condition.Сompleted) {
            text.paintFlags = text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }


        val starSelector = ImageView(context).apply {
            id = View.generateViewId()
            setImageResource(R.drawable.star_selector)
            isClickable = true
            if(task_!!.importance == ImportanceTask.Easy){
                isSelected = false
            }else if(task_!!.importance == ImportanceTask.Hard){
                isSelected = true
            }
            setOnClickListener {
                Log.d("STAR_TAG", "star clicked, task=${task_!!.importance}")
                isSelected = !isSelected
                val builder = Task.Builder().apply {
                    setName(task_!!.name)
                    setBody(task_!!.body)
                    setCondition(task_!!.condition)
                    setCompleteTime(task_!!.completeTime)
                    setCreateTime(task_!!.createTime)
                    setRepetition(task_!!.repetition1)
                    setReminder(task_!!.reminder)
                }
                if (isSelected){
                    builder.setImportance(ImportanceTask.Hard)
                }else{
                    builder.setImportance(ImportanceTask.Easy)

                }

                viewModel.updateTask(builder.Build(),index)

            }
        }

        constraint.addView(text)
        constraint.addView(starSelector)

        val constraintSet = ConstraintSet()
        constraintSet.clone(constraint)
        constraintSet.connect(
            text.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START,
            180
        )
        constraintSet.connect(
            text.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP,
            0
        )
        constraintSet.connect(
            starSelector.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START,
            20
        )
        constraintSet.connect(
            starSelector.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP,
            0
        )
        constraintSet.applyTo(constraint)



        constraint.background = R.color.black.toDrawable()
//                                constraint.isClickable = true
//                                constraint.setOnClickListener {
//                                    constraint.visibility = View.GONE
//                                    if (index < tasks.size) {
//                                        deleteTask(index)
//                                        println(task.body)
//                                    }
//                                }


        constraint.layoutParams = linearParam
        constraint.setBackgroundResource(R.drawable.background_task)
        scroll.addView(constraint)
    }




}