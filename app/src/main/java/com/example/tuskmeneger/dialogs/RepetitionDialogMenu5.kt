package com.example.tuskmeneger.dialogs

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.tuskmeneger.FullscreenFragmentForm
import com.example.tuskmeneger.logic.Task
import com.example.tuskmeneger.saportclassis.TimeGetter

class RepetitionDialogMenu5(
    val build: Task.Builder,
    val fragment: FullscreenFragmentForm
): RepetitionDialogMenu4(build) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(this.context.toString(),"create")
    }

    override fun close() {
        super.close()
        fragment.restart()
    }

    override fun TimeGetterListener(timeGetter: TimeGetter?) {
        super.TimeGetterListener(timeGetter)
        fragment.restart()
    }
}