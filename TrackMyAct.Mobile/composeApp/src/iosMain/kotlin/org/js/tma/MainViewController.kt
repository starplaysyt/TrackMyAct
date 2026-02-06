package org.js.tma

import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.window.ComposeUIViewController
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.UIKit.UIApplicationWillTerminateNotification

fun MainViewController() = ComposeUIViewController {

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

    App()
}