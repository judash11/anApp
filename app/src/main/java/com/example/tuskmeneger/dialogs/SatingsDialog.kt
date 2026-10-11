package com.example.tuskmeneger.dialogs

import android.content.Context
import android.view.LayoutInflater
import com.example.tuskmeneger.R
import com.example.tuskmeneger.saportclassis.TimeGetter
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.DayOfWeek
import android.util.Log


class SatingsDialog {
    fun create(context: Context, onResult: (TimeGetter?) -> Unit) {
        var week: DayOfWeek? = null
        var month = false
        var year = false
        var day = false

        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_activity1, null)

        val dayIdToDow = mapOf(
            R.id.constraintLayoutPnBut to DayOfWeek.MONDAY,
            R.id.constraintLayoutVtBut to DayOfWeek.TUESDAY,
            R.id.constraintLayoutSrBut to DayOfWeek.WEDNESDAY,
            R.id.constraintLayoutHtBut to DayOfWeek.THURSDAY,
            R.id.constraintLayoutPtBut to DayOfWeek.FRIDAY,
            R.id.constraintLayoutSbBut to DayOfWeek.SATURDAY,
            R.id.constraintLayoutVsBut to DayOfWeek.SUNDAY,
        )

        val buttons = mutableListOf<MaterialButton>()

        dayIdToDow.forEach { (id, dow) ->
            val btn = dialogView.findViewById<MaterialButton>(id)
            if (btn == null) {
                Log.w("SatingsDialog", "Кнопка с id=$id не найдена в dialog_activity1.xml")
                return@forEach
            }
            buttons += btn
            btn.addOnCheckedChangeListener { view, isChecked ->
                if (isChecked) {
                    week = dow
                    buttons.forEach { other -> if (other !== view) other.isChecked = false }
                } else if (week == dow) {
                    week = null
                }
            }
        }

        MaterialAlertDialogBuilder(context)
            .setView(dialogView)
            .setPositiveButton("Готово") { _, _ ->
                onResult(TimeGetter(week, month, year, day))
            }
            .setNegativeButton("Отмена") { _, _ ->
                onResult(null)
            }
            .setOnCancelListener {
                onResult(null)
            }
            .show()
    }
}
//class SatingsDialog {
//    fun create(context: Context, onResult: (TimeGetter?) -> Unit) {
//        var week: DayOfWeek? = null
//        var month: Boolean = false
//        var year: Boolean = false
//        var day: Boolean = false
//
//        val dialogView = LayoutInflater
//            .from(context)
//            .inflate(R.layout.dialog_activity1, null)
//
//
//        val log = Toast.makeText(context, "PN", Toast.LENGTH_SHORT)
//
//        val dialog = MaterialAlertDialogBuilder(context)
//            .setView(dialogView)
//            .setPositiveButton("Готово"){_,_->
//                onResult(TimeGetter(week, month, year, day))
//
//            }
//            .setNegativeButton("Отмена"){_,_->
////                Toast.makeText(context, "PN", Toast.LENGTH_SHORT).show()
//                onResult(null)
//            }
//            .setOnCancelListener {
//                onResult(null)
//            }
//            .create()
//
//
//
////        val buttonFirstDayOfWeek = dialogView.findViewById<MaterialButton>(R.id.constraintLayoutPnBut)
////        buttonFirstDayOfWeek.addOnCheckedChangeListener { materialButton, bool ->
////            print("pn")
////            log.show()
////            if (bool){
////                val time = DayOfWeek.MONDAY
////                week = time
////            }
////        }
////
////        val buttonTwoDayOfWeek = dialogView.findViewById<MaterialButton>(R.id.constraintLayoutVtBut)
////        buttonTwoDayOfWeek.addOnCheckedChangeListener { materialButton, bool ->
////            print("vt")
////            log.show()
////            if (bool){
////                val time = DayOfWeek.TUESDAY
////                week = time
////            }
////        }
////
////        val buttonFreeDayOfWeek = dialogView.findViewById<MaterialButton>(R.id.constraintLayoutSrBut)
////        buttonFreeDayOfWeek.addOnCheckedChangeListener { materialButton, bool ->
////            print("sr")
////            log.show()
////            if (bool){
////                val time = DayOfWeek.WEDNESDAY
////                week = time
////            }
////        }
////
////        val buttonFourDayOfWeek = dialogView.findViewById<MaterialButton>(R.id.constraintLayoutHtBut)
////        buttonFourDayOfWeek.addOnCheckedChangeListener { materialButton, bool ->
////            print("ht")
////            log.show()
////            if (bool){
////                val time = DayOfWeek.THURSDAY
////                week = time
////            }
////        }
////
////        val buttonFiveDayOfWeek = dialogView.findViewById<MaterialButton>(R.id.constraintLayoutPtBut)
////        buttonFiveDayOfWeek.addOnCheckedChangeListener { materialButton, bool ->
////            print("pt")
////            log.show()
////            if (bool){
////                val time = DayOfWeek.FRIDAY
////                week = time
////            }
////        }
////
////        val buttonSixDayOfWeek = dialogView.findViewById<MaterialButton>(R.id.constraintLayoutSbBut)
////        buttonSixDayOfWeek.addOnCheckedChangeListener { materialButton, bool ->
////            print("sb")
////            log.show()
////            if (bool){
////                val time = DayOfWeek.SATURDAY
////                week = time
////            }
////        }
////
////        val buttonSevenDayOfWeek = dialogView.findViewById<MaterialButton>(R.id.constraintLayoutVsBut)
////        buttonSevenDayOfWeek.addOnCheckedChangeListener { materialButton, bool ->
////            print("sr")
////            log.show()
////            if (bool){
////                val time = DayOfWeek.SUNDAY
////                week = time
////            }
////        }
//
//        val ids = listOf(
//            R.id.constraintLayoutPnBut to DayOfWeek.MONDAY,
//            R.id.constraintLayoutVtBut to DayOfWeek.THURSDAY,
//            R.id.constraintLayoutSrBut to DayOfWeek.WEDNESDAY,
//            R.id.constraintLayoutHtBut to DayOfWeek.THURSDAY,
//            R.id.constraintLayoutPtBut to DayOfWeek.FRIDAY,
//            R.id.constraintLayoutSbBut to DayOfWeek.SATURDAY,
//            R.id.constraintLayoutVsBut to DayOfWeek.SUNDAY,
//        )
//
//        val buttons = mutableListOf<MaterialButton>()
//
//        ids
//
//        buttons.forEach { btn ->
//            btn.addOnCheckedChangeListener { view, isChecked ->
//                if (isChecked) {
//                    buttons.forEach { other ->
//                        if (other !== view) other.isChecked = false
//                    }
//                }
//            }
//        }
//        dialog.show()
////        val returnValue = TimeGetter(week,month,year,day)
////        return returnValue
//    }
//}