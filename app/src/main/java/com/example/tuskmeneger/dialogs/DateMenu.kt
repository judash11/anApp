package com.example.tuskmeneger.dialogs


import android.content.Context

import android.os.Bundle

import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

import androidx.fragment.app.Fragment
import com.example.tuskmeneger.MyDayLayout
import com.example.tuskmeneger.R
import com.example.tuskmeneger.important_layout
import com.example.tuskmeneger.logic.Task
import com.example.tuskmeneger.repository.Condition
import com.example.tuskmeneger.repository.ImportanceTask
import com.example.tuskmeneger.repository.RepositoryBD
import com.example.tuskmeneger.saportclassis.ActivI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.time.LocalDateTime


class DateMenu(
//      val calendarFrameId: Int,
//    val priority: ImportanceTask
) : Fragment(R.layout.dialog_date_menu1) {

    companion object{
        var FAB = "FAB"
        var CALENDARFRAMEID = "calendarFrameId"

        var PRIORITY = "priority"
        fun newInstance(fab: Int,cal: Int,pri: ImportanceTask): DateMenu{
            return DateMenu().apply {
                arguments = bundleOf(
                    FAB to fab,
                    CALENDARFRAMEID to cal,
                    PRIORITY to pri,
                )
            }
        }
    }

    private val taskBuilder = Task.Builder()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    val tasksButton = view.findViewById<FrameLayout>(R.id.tusksButton)
    tasksButton?.setOnClickListener {
        // TaskClickListner()
    }

    val fab = arguments?.getInt(FAB)
    val calendarFrameId = arguments?.getInt(CALENDARFRAMEID)



    val calendarFrame: FrameLayout = requireActivity()
        .findViewById<FrameLayout>(calendarFrameId!!)
        ?: error("не найден CalendarFrame")


    val calendarButton = view.findViewById<FrameLayout>(R.id.calendarButton)
    calendarButton.setOnClickListener {
        calendarFrame.visibility = View.VISIBLE
        val calendar = parentFragmentManager.findFragmentByTag("calendar1")
        println("calendar")
        if (calendar == null) {
            parentFragmentManager.beginTransaction()
                .replace(calendarFrame.id, CalendarDialogMenu2( taskBuilder), "calendar1")
                .commit()
        }

    }





    val notificationButton = view.findViewById<FrameLayout>(R.id.notificationButton)
    notificationButton?.setOnClickListener {
        val notification = parentFragmentManager.findFragmentByTag("notification")
        calendarFrame.visibility = View.VISIBLE
        println("notification")
        if(notification == null) {
            parentFragmentManager.beginTransaction()
                .replace(calendarFrame.id, NotificationDialogMenu3(taskBuilder), "notification")
                .commit()
        }
    }


    val repetitionButton = view.findViewById<FrameLayout>(R.id.repetitionButton)
    repetitionButton?.setOnClickListener {
        val repetition = parentFragmentManager.findFragmentByTag("repetition")
        calendarFrame.visibility = View.VISIBLE
        println("repetition")
        if(repetition == null) {
            parentFragmentManager.beginTransaction()
                .replace(calendarFrame.id, RepetitionDialogMenu4(taskBuilder), "repetition")
                .commit()
        }
    }







    val editText = view.findViewById<EditText>(R.id.editText1)

    val arrowButton = view.findViewById<ImageView>(R.id.arrowButton2)


    val imageButtonFab = requireActivity()
        .findViewById<ImageView>(fab!!)

    if (editText != null && arrowButton != null && imageButtonFab != null) {
        arrowButton.setOnClickListener {
            arrowClickListener(editText, imageButtonFab,calendarFrame)
        }
    }

    if (editText != null) {
        showKeyboard(editText)
    }

    editText.setOnEditorActionListener { view, i, event ->
        arrowClickListener(editText, imageButtonFab,calendarFrame)

    }
}

    fun arrowClickListener(view: EditText, viewFAB: ImageView, viewCal: FrameLayout): Boolean {
        println("arr")

        hideKeyboard(view)
        val text: String = view.text.toString()

//        if (text.isNotBlank()) {

        val calendar = parentFragmentManager.findFragmentByTag("calendar1")

        val notification = parentFragmentManager.findFragmentByTag("notification")

        val priority: ImportanceTask = arguments?.get(PRIORITY) as ImportanceTask



        parentFragmentManager.beginTransaction().apply {

        taskBuilder.setCreateTime(LocalDateTime.now())
        val editText = view.findViewById<EditText>(R.id.editText1)
        taskBuilder.setName(editText.text.toString())
        taskBuilder.setImportance(priority)
        taskBuilder.setCondition(Condition.InProgress)

        if(taskBuilder.name != "") {
            val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
            val finalTusk = taskBuilder.Build()

            scope.launch { RepositoryBD().Build(finalTusk) }
            scope.launch { (requireActivity() as ActivI).Scroller() }
        }
        if (calendar != null) {
            println("ccc1")
            remove(calendar)
        }
        if (notification != null) {
                    println("ccc2")
                    remove(notification)
        }

                remove(this@DateMenu)


        }.commit()

            viewFAB.visibility = View.VISIBLE
        viewCal.visibility = View.GONE



//                val activity: ActivI = requireActivity()

        println(taskBuilder.toString())


        //sdfsdfdsfsdfsfdssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss

        return false
    }


    fun showKeyboard(edit: EditText) {
        edit.requestFocus()
        edit.post {
            val imm = edit.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            imm.showSoftInput(edit, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hideKeyboard(edit: EditText) {
        val imm = edit.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        imm.hideSoftInputFromWindow(edit.windowToken, 0)
    }


}