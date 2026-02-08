package org.js.tma.service

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.js.tma.dto.AuthResponse
import org.js.tma.dto.LoginRequest
import org.js.tma.viewmodel.LoadingState

class LoginService(private val httpKtorService: HttpKtorService) {

    suspend fun login(
        username: String,
        password: String
    ) : LoadingState {
        val response = httpKtorService.getClient().post("http://localhost:5241/user/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(username, password))
        }

        return if (response.status.value == 200) {
            if ((response.body() as AuthResponse).isSuccess) LoadingState.Success
            else LoadingState.Failed("Unauthorized")
        }
            else LoadingState.Failed(error = "HTTP error ${response.status.value}")
    }

}