package org.js.tma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import okio.FileSystem
import okio.Path.Companion.toPath
import org.js.tma.service.PersistentCookieStorage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val path = "${filesDir}/cookies.json".toPath()
        val storage = PersistentCookieStorage(filePath = path, FileSystem.SYSTEM)

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                storage = storage,
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppCloser.close()
    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}