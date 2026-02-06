package org.js.tma.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.launch
import org.js.tma.data.stateFlow
import org.js.tma.dto.RegisterParticipantRequest
import org.js.tma.service.HttpKtorService
import kotlin.reflect.KClass

@Stable
class ParticipantRegistrationViewModel(private val httpKtorService: HttpKtorService): ViewModel() {

    val nicknameField by stateFlow("")
    val emailField by stateFlow("")
    val passwordField by stateFlow("")
    val fullNameField by stateFlow("")
    val phoneField by stateFlow("")
    val birthdayField by stateFlow("")

    val sendingState by stateFlow<LoadingState>(LoadingState.NotStarted)

    fun sendRegisterRequest() {
        viewModelScope.launch {
            sendingState.value = LoadingState.Loading
            try {
                sendingState.value = register(nicknameField.value, emailField.value, passwordField.value, fullNameField.value, phoneField.value, birthdayField.value)
            } catch (e: Exception) {
                sendingState.value = LoadingState.Failed(error = e.message ?: "Unknown error")
            }
        }
    }

    private suspend fun register(
        nickname: String,
        email: String,
        password: String,
        fullName: String,
        phone: String,
        birthday: String,
    ) : LoadingState {
        val registerParticipantRequest = RegisterParticipantRequest(nickname, email, password, fullName, phone, birthday)

        val response = httpKtorService.getClient().post("http://localhost:5241/user/particiant/register") {
            contentType(ContentType.Application.Json)
            setBody(registerParticipantRequest)
        }

        return if (response.status.value == 200) LoadingState.Success
            else LoadingState.Failed(error = "Registration error")
    }

}

class ParticipantRegistrationViewModelFactory(private val httpKtorService: HttpKtorService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        if (modelClass.equals(ParticipantRegistrationViewModel::class)) {
            @Suppress("UNCHECKED_CAST")
            return ParticipantRegistrationViewModel(httpKtorService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}