package org.js.tma.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import org.js.tma.data.stateFlow
import org.js.tma.service.HttpKtorService
import org.js.tma.service.RegistrationService
import kotlin.reflect.KClass

@Stable
class ParticipantRegistrationViewModel(private val httpKtorService: HttpKtorService): ViewModel() {

    val loginField by stateFlow("")
    val emailField by stateFlow("")
    val passwordField by stateFlow("")
    val fullNameField by stateFlow("")
    val phoneField by stateFlow("")
    val birthdayField by stateFlow("")

    val sendingState by stateFlow<LoadingState>(LoadingState.NotStarted)

    private val service = RegistrationService(httpKtorService)

    fun sendRegisterRequest() {
        viewModelScope.launch {
            sendingState.value = LoadingState.Loading
            try {
                sendingState.value = service.registerParticipant(loginField.value, emailField.value, passwordField.value, fullNameField.value, phoneField.value, birthdayField.value)
            } catch (e: Exception) {
                print(e.stackTraceToString())
                sendingState.value = LoadingState.Failed(error = e.message ?: "Unknown error")
            }
        }
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