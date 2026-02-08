package org.js.tma

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.ktor.client.plugins.cookies.CookiesStorage
import org.js.tma.service.HttpKtorService
import org.js.tma.service.PersistentCookieStorage
import org.js.tma.wrapper.AppPreviewWrapper

@Composable
fun App(storage: PersistentCookieStorage) {
    AppPreviewWrapper(
        httpKtorService = HttpKtorService(
            storage
        )
    )
}