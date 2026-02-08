package org.js.tma.service

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.js.tma.dto.RegisterOrganizerRequest
import org.js.tma.dto.RegisterParticipantRequest
import org.js.tma.viewmodel.LoadingState

class RegistrationService(private val httpKtorService: HttpKtorService) {

    suspend fun registerParticipant(
        login: String,
        email: String,
        password: String,
        fullName: String,
        phone: String,
        birthday: String,
    ) : LoadingState {
        val registerParticipantRequest = RegisterParticipantRequest(
            login,
            email,
            password,
            fullName,
            phone,
            LocalDateTime.parse(birthday.replace('.', '-') + "T00:00:00.000"))

        val response = httpKtorService.getClient().post("http://localhost:5241/user/particiant/register") {
            contentType(ContentType.Application.Json)
            setBody(registerParticipantRequest)
        }

        return if (response.status.value == 200) LoadingState.Success
        else LoadingState.Failed(error = "Registration error")
    }

    suspend fun registerOrganizer(
        login: String,
        email: String,
        password: String,
        name: String,
        key: String,
        organization: String,
    ) : LoadingState {
        val registerOrganizerRequest = RegisterOrganizerRequest(
            login,
            email,
            password,
            name,
            organization,
        )

        val response = httpKtorService.getClient().post("http://localhost:5241/user/organizer/register") {
            contentType(ContentType.Application.Json)
            setBody(registerOrganizerRequest)
        }

        return if (response.status.value == 200) LoadingState.Success
        else LoadingState.Failed(error = "Registration error")
    }

}