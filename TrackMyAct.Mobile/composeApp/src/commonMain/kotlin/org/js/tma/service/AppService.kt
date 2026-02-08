package org.js.tma.service

import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.js.tma.viewmodel.LoadingState

class AppService(private val httpKtorService: HttpKtorService) {

    suspend fun checkLogIn() : LoadingState {
        val response = httpKtorService.getClient().get("http://localhost:5241/user/checkLogIn") {
            contentType(ContentType.Application.Json)
        }

        return if (response.status.value == 200) LoadingState.Authorized else LoadingState.Unauthorized
    }

}