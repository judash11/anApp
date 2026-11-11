package com.example.tuskmeneger.dialogs

import android.os.Bundle
import android.view.View
import com.example.tuskmeneger.FullscreenFragmentForm
import com.example.tuskmeneger.logic.Task

class CalendarDialog3(
    val builder: Task.Builder,
    val dialog: FullscreenFragmentForm
) : CalendarDialogMenu2(builder) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun close() {
        super.close()
        dialog.restart()
    }
}