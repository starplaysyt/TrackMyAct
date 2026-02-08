package org.js.tma

import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import okio.FileSystem
import okio.Path.Companion.toPath
import org.js.tma.service.PersistentCookieStorage
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIApplicationWillTerminateNotification

@OptIn(ExperimentalForeignApi::class)
fun MainViewController() = ComposeUIViewController {

    val path = NSSearchPathForDirectoriesInDomains(
        NSDocumentDirectory,
        NSUserDomainMask,
        true
    ).firstOrNull() as? String

    val finalPath = "$path/cookies.json".toPath()
    val storage = PersistentCookieStorage(finalPath, fileSystem = FileSystem.SYSTEM)

    DisposableEffect(Unit) {
        val notificationCenter = NSNotificationCenter.defaultCenter
        val mainQueue = NSOperationQueue.mainQueue

        val terminateObserver = notificationCenter.addObserverForName(
            name = UIApplicationWillTerminateNotification,
            `object` = null,
            queue = mainQueue
        ) { _ ->
            AppCloser.close()
        }

        onDispose {
            notificationCenter.removeObserver(terminateObserver)
        }
    }

    App(
        storage = storage,
    )
}