package com.example.tuskmeneger.dialogs

import android.content.Context
import android.view.LayoutInflater
import com.example.tuskmeneger.R
import com.example.tuskmeneger.saportclassis.TimeGetter
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.DayOfWeek
import android.util.Log


class SatingDialog {
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
                Log.w("SatingDialog", "Кнопка с id=$id не найдена в dialog_activity1.xml")
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
