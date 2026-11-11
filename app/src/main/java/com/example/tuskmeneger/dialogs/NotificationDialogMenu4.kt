package com.example.tuskmeneger.dialogs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.tuskmeneger.FullscreenFragmentForm
import com.example.tuskmeneger.logic.Task

class NotificationDialogMenu4(
    val builderTask: Task.Builder,
    val fullscreenFragmentForm: FullscreenFragmentForm
): NotificationDialogMenu3(builderTask) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun close(){
        super.close()
        fullscreenFragmentForm.restart()
    }


}