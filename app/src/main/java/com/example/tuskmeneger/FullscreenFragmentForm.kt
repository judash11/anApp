package com.example.tuskmeneger

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RestrictTo
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tuskmeneger.dialogs.CalendarDialog3
import com.example.tuskmeneger.dialogs.CalendarDialogMenu2

import com.example.tuskmeneger.dialogs.NotificationDialogMenu3
import com.example.tuskmeneger.dialogs.NotificationDialogMenu4
import com.example.tuskmeneger.dialogs.RepetitionDialogMenu4
import com.example.tuskmeneger.dialogs.RepetitionDialogMenu5
import com.example.tuskmeneger.logic.Task
import com.example.tuskmeneger.logic.TaskGet
import com.example.tuskmeneger.repository.Condition
import com.example.tuskmeneger.repository.ImportanceTask
import com.example.tuskmeneger.repository.ReminderTask
import com.example.tuskmeneger.repository.Repetition
import com.example.tuskmeneger.repository.RepositoryBD
import com.example.tuskmeneger.repository.TaskViewModel
import com.example.tuskmeneger.saportclassis.Converter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import kotlin.coroutines.coroutineContext


class FullscreenFragmentForm(
//    val backText: String,
//    val builder: Task.Builder
) : Fragment(R.layout.fragment_fullscreen_task_form) {


    companion object {
        var TEXT = "text"
        var TASK = "task"
        var CONTAINER = "container"

        fun newInstance(text: String, task: Int,container: Int): FullscreenFragmentForm {
            return FullscreenFragmentForm().apply {
                arguments = bundleOf(
                    TEXT to text,
                    TASK to task,
                    CONTAINER to container
                )

            }
        }
    }

    private val viewModel: TaskViewModel by viewModels()
    private var builder: Task.Builder? = null
    private var view_: View? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val taskIndex = arguments?.getInt(TASK)
//        var task1: Task
        val text1 = arguments?.getString(TEXT)

        view_ = view
        viewModel.getOne(taskIndex!!) { task ->
        builder = Converter().toBuilder(task!!)

            val tvView = view.findViewById<TextView>(R.id.textView31)
            tvView.setText(builder!!.name)

            val completeTime = view.findViewById<ImageButton>(R.id.imageButton111)
            val completeTimeText = view.findViewById<EditText>(R.id.editTextText3)
            val main = view.findViewById<ConstraintLayout>(R.id.mainFrame)


            completeTimeText.apply {
                setText(Converter()
                    .toComplete(
                        builder!!
                    .completeTime
                    )
                )
                isClickable.not()
            }


            completeTime.setOnClickListener {
                val container = R.id.complete_frame
                val fragment = CalendarDialog3(builder!!,this@FullscreenFragmentForm)
                parentFragmentManager.beginTransaction()
                    .replace(container,fragment)
                    .commit()

                main.setOnClickListener {
                    parentFragmentManager.beginTransaction()
                        .remove(fragment)
                        .commit()
                }


            }


            val repeatText = view.findViewById<EditText>(R.id.editTextText4)
            val txt: String = when (builder!!.repetition1){
                Repetition.SatingThursday -> "по четвергам"
                Repetition.SatingSunday -> "по воскресениям"
                Repetition.SatingFriday -> "по пятницам"
                Repetition.SatingMonday -> "по понедельникам"
                Repetition.SatingSaturday -> " начиная с субботы"
                Repetition.EveryYear ->  "каждый год"
                Repetition.EveryMonth -> "каждый месяц"
                Repetition.EveryWeek ->  "каждую неделю"
                Repetition.EveryWorkDay -> "каждый рабочий день"
                Repetition.EveryDay ->  "каждый день"
                Repetition.SatingWednesday -> "по средам"
                Repetition.SatingTuesday -> "по вторникам"
                else -> "-"
            }

            repeatText.apply {
                setText(txt)
                isClickable = false
            }
            val repeatBut = view.findViewById<ImageButton>(R.id.imageButton11)
            repeatBut.setOnClickListener {
                Log.d(this.context.toString(),"press butt")

                val container = R.id.notification_frame
                view.findViewById<FrameLayout>(container).visibility = View.VISIBLE
                val fragment = RepetitionDialogMenu5(builder!!,this@FullscreenFragmentForm)
                parentFragmentManager.beginTransaction()
                    .replace(container,fragment)
                    .commit()

                main.setOnClickListener {
                    parentFragmentManager.beginTransaction()
                        .remove(fragment)
                        .commit()
                }
            }



             val bodySelect = view.findViewById<EditText>(R.id.editTextText5)

            val toBackText = view.findViewById<TextView>(R.id.textView69)
            toBackText.apply {
                text = text1.toString()
            }

            bodySelect.apply {
                setText(builder!!.body)
            }

            val arrow = view.findViewById<ImageView>(R.id.imageView299)
            arrow.apply {

                setOnClickListener {
                    println("destroy")
                    builder!!.setBody(bodySelect.text.toString())
                    val result = builder!!.Build()


                    val scope = CoroutineScope(SupervisorJob()+ Dispatchers.IO)
                    scope.launch {
                        RepositoryBD().update(taskIndex, result)
                    }
                    requireActivity().recreate()


                    Close()


                }


            }

            val selectTF = view.findViewById<ImageView>(R.id.imageView31)
            selectTF.apply {
                val condition = builder!!.condition
                if (condition == Condition.InProgress) {
                    isSelected = false
                } else if (condition == Condition.Сompleted) {
                    isSelected = true
                }
                else if(condition == Condition.Overdue){
                    setImageResource(R.drawable.hause3)
                }


                isClickable = true

                setOnClickListener {
                    isSelected = !isSelected
                    if (isSelected) {
                        builder!!.setCondition(Condition.Сompleted)
                    } else {
                        builder!!.setCondition(Condition.InProgress)
                    }
                }

            }

            val startSelect = view.findViewById<ImageView>(R.id.imageView32)
            startSelect.apply {
                val condition = builder!!.importance
                if (condition == ImportanceTask.Easy) {
                    isSelected = false
                } else if (condition == ImportanceTask.Hard) {
                    isSelected = true
                }


                isClickable = true

                setOnClickListener {
                    isSelected = !isSelected
                    if (isSelected) {
                        builder!!.setImportance(ImportanceTask.Hard)
                    } else {
                        builder!!.setImportance(ImportanceTask.Easy)
                    }
                }
            }

            val notification = view.findViewById<ImageButton>(R.id.imageButton3232)
            val editNotification = view.findViewById<EditText>(R.id.editTextText)
            val reminder = Converter().toReminder(builder!!.reminder)
            notification.apply {
                println(reminder.toString())
                val txt: String = when (reminder){
                  ReminderTask.Today -> "сегодня"
                  ReminderTask.Tomorrow -> "завтра"
                  ReminderTask.NextWeek -> "на следующей неделе"
                  ReminderTask.SetDate -> builder!!.reminder.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                  else -> "-"
                }

                editNotification.setText(txt)


                setOnClickListener {
                    println("cdccd")
                    val container = R.id.notification_frame
                    val frame = view.findViewById<FrameLayout>(container)
                    frame.visibility = View.VISIBLE
                    val fragment = NotificationDialogMenu4(builder!!,this@FullscreenFragmentForm)
                    parentFragmentManager.beginTransaction()
                        .replace(container, fragment)
                        .commit()


                    main.setOnClickListener {
                        parentFragmentManager.beginTransaction()
                            .remove(fragment)
                            .commit()
                    }

                }
            }

            val deleteButton = view_!!.findViewById<ImageView>(R.id.garbage)
            deleteButton.setOnClickListener {

                val scope = CoroutineScope(SupervisorJob()+ Dispatchers.IO)
                scope.launch {
                    RepositoryBD().deleteTask(taskIndex)
                }
                requireActivity().recreate()

                Close()
            }



        }

    }
    fun restart() {
//        val container = arguments?.getInt(CONTAINER)
//        if (container != null) {
//            parentFragmentManager.beginTransaction()
//                .replace(container,this)
//                .commit()
//        }
        val notification = view_!!.findViewById<ImageButton>(R.id.imageButton3232)
        val editNotification = view_!!.findViewById<EditText>(R.id.editTextText)
        val reminder = Converter().toReminder(builder!!.reminder)
        notification.apply {
            println(reminder.toString())
            val txt: String = when (reminder) {
                ReminderTask.Today -> "сегодня"
                ReminderTask.Tomorrow -> "завтра"
                ReminderTask.NextWeek -> "на следующей неделе"
                ReminderTask.SetDate -> builder!!.reminder.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                else -> "-"
            }

            editNotification.setText(txt)
        }


        val completeTimeText = view_!!.findViewById<EditText>(R.id.editTextText3)
        completeTimeText.apply {
            setText(
                Converter()
                .toComplete(
                    builder!!
                    .completeTime
                )
            )
            isClickable.not()
        }
        val repeatText = view_!!.findViewById<EditText>(R.id.editTextText4)
        val txt: String = when (builder!!.repetition1){
            Repetition.SatingThursday -> "по четвергам"
            Repetition.SatingSunday -> "по воскресениям"
            Repetition.SatingFriday -> "по пятницам"
            Repetition.SatingMonday -> "по понедельникам"
            Repetition.SatingSaturday -> " начиная с субботы"
            Repetition.EveryYear ->  "каждый год"
            Repetition.EveryMonth -> "каждый месяц"
            Repetition.EveryWeek ->  "каждую неделю"
            Repetition.EveryWorkDay -> "каждый рабочий день"
            Repetition.EveryDay ->  "каждый день"
            Repetition.SatingWednesday -> "по средам"
            Repetition.SatingTuesday -> "по вторникам"
            else -> "-"
        }

        repeatText.apply {
            setText(txt)
            isClickable = false
        }



    }

    fun Close(){
        parentFragmentManager.beginTransaction()
            .remove(this@FullscreenFragmentForm)
            .commit()
    }
}
