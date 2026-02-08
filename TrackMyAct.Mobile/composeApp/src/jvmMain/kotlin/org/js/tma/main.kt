package org.js.tma

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import okio.FileSystem
import okio.Path.Companion.toPath
import org.js.tma.service.PersistentCookieStorage

fun main() = application {
    val path = "cookies.json".toPath()
    val storage = PersistentCookieStorage(path, fileSystem = FileSystem.SYSTEM)

    Window(
        onCloseRequest = {
            AppCloser.close()
            exitApplication()
        },
        state = remember { WindowState(size = DpSize(400.dp, 800.dp)) },
        title = "TrackMyAct",
    ) {
        App(
            storage = storage,
        )
    }
}