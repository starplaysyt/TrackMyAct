package org.js.tma.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import org.js.tma.data.stateFlow
import org.js.tma.service.AppService
import org.js.tma.service.HttpKtorService
import org.js.tma.wrapper.AppBarPage
import org.js.tma.wrapper.AppScreen
import kotlin.reflect.KClass

@Stable
class AppViewModel(private val httpKtorService: HttpKtorService): ViewModel() {

    val currentScreen by stateFlow(AppScreen.Test)
    val currentBottomBarSelected by stateFlow(AppBarPage.MAIN)
    val appState by stateFlow<LoadingState>(LoadingState.NotStarted)
    val isLogin by stateFlow(false)
    private val service = AppService(httpKtorService)

    fun checkLogIn() {
        viewModelScope.launch {
            appState.value = LoadingState.Loading
            try {
                when (service.checkLogIn()) {
                    LoadingState.Authorized -> {
                        isLogin.value = true
                        currentScreen.value = AppScreen.MAIN
                    }
                    LoadingState.Unauthorized -> {
                        currentScreen.value = AppScreen.LOGIN
                    }
                    else -> {
                        currentScreen.value = AppScreen.LOGIN
                    }
                }
                appState.value = LoadingState.Success
            } catch (_: Exception) {
                currentScreen.value = AppScreen.LOGIN
                appState.value = LoadingState.Success
            }
        }
    }

}

class AppViewModelFactory(private val httpKtorService: HttpKtorService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        if (modelClass.equals(AppViewModel::class)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(httpKtorService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}