package com.example.tuskmeneger.dialogs

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.ContentInfo
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.tuskmeneger.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DateMenu(
//    @LayoutRes val resources: Int
) : Fragment(R.layout.dialog_date_menu1) {
//    private lateinit var editText: EditText
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dialogView = LayoutInflater
            .from(context)
            .inflate(R.layout.dialog_date_menu1, null)

//        val viewMain = R.layout.my_day_layout

//        val dialog = MaterialAlertDialogBuilder(context)
//            .setView(dialogView)
//            .create()









        val tasksButton = dialogView.findViewById<FrameLayout>(R.id.tusksButton)
        tasksButton.setOnClickListener {
//            TaskClickListner()
        }



        val calendarButton = dialogView.findViewById<FrameLayout>(R.id.calendarButton)
        calendarButton.setOnClickListener {
           val calendar = CalendarDialogMenu2(context,dialogView)
            calendar.create()
        }



        val notificationButton = dialogView.findViewById<FrameLayout>(R.id.notificationButton)
        notificationButton.setOnClickListener {
//            NotificationClickListner(timeFrame,
//                datePickerDialog,
//                thisDayTimeBut,
//                nextDayTimeFrame,
//                nextWeekTimeBut,
//                setDateTimeBut,
//                localTime)
        }




        val repetitionButton = dialogView.findViewById<FrameLayout>(R.id.repetitionButton)
        repetitionButton.setOnClickListener {
//            RepetitionClickListner(everyDayBut,
//                everyWorkDayBut,
//                everyWeekBut,
//                everyMonthBut,
//                everyYearBut,
//                satingEvery,
//                repetitionsMenu
//            )
        }



        val imageButtonFab = view.findViewById<ImageView>(R.id.imageFABButtonMDLayout)

        val editText: EditText = dialogView.findViewById<EditText>(R.id.editText1)

        val arrowButton = view.findViewById<ImageView>(R.id.arrowButton2)


        arrowButton.setOnClickListener {
            arrowClickListener(editText, arrowButton, imageButtonFab)
        }





//        dialog.show()

        showKeyboard(editText)

    }
        fun arrowClickListener(view: EditText, viewContainer: ImageView, viewFAB: ImageView){
            println("arr")
            val text: String = view.text.toString()
            if (text.isNotBlank()){
                viewContainer.visibility = View.GONE
                viewFAB.visibility = View.VISIBLE
                hideKeyboard(view)
            }
        }




    fun showKeyboard(edit: EditText){
        edit.requestFocus()
        edit.post{
            val imm = edit.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            imm.showSoftInput(edit, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hideKeyboard(edit: EditText){
        val imm =edit.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edit.windowToken, 0)
    }

}