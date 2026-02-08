package org.js.tma.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import org.js.tma.data.stateFlow
import org.js.tma.service.HttpKtorService
import org.js.tma.service.LoginService
import kotlin.reflect.KClass

@Stable
class LoginViewModel(private val httpKtorService: HttpKtorService): ViewModel() {

    val usernameField by stateFlow("")
    val passwordField by stateFlow("")
    val state by stateFlow<LoadingState>(LoadingState.NotStarted)
    private val service = LoginService(httpKtorService)

    fun login() {
        viewModelScope.launch {
            state.value = LoadingState.Loading
            try {
                state.value = service.login(usernameField.value, passwordField.value)
            } catch (e: Exception) {
                state.value = LoadingState.Failed(e.message ?: "HTTP error ${e.cause}")
            }
        }
    }

}

class LoginViewModelFactory(private val httpKtorService: HttpKtorService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        if (modelClass.equals(LoginViewModel::class)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(httpKtorService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}