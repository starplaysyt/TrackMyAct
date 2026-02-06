package org.js.tma

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = {
            AppCloser.close()
            exitApplication()
        },
        title = "TrackMyAct",
    ) {
        App()
    }
}