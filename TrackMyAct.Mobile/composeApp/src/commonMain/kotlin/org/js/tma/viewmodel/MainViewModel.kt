package org.js.tma.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import org.js.tma.data.stateFlow
import org.js.tma.dto.EventEntity
import org.js.tma.service.HttpKtorService
import org.js.tma.service.MainService
import kotlin.reflect.KClass

@Stable
class MainViewModel(private val httpKtorService: HttpKtorService): ViewModel() {

    val events by stateFlow(emptyList<EventEntity>())
    val eventState by stateFlow<LoadingState>(LoadingState.NotStarted)
    private val service = MainService(httpKtorService)

    fun loadEvents() {
        viewModelScope.launch {
            eventState.value = LoadingState.Loading
            try {
                val response = service.getAllEvents()
                if (response.second is LoadingState.Success) {
                    events.value = response.first!!
                    eventState.value = LoadingState.Success
                }
                else eventState.value = response.second
            } catch (e: Exception) {
                eventState.value = LoadingState.Failed(e.message ?: "Unknown error")
            }
        }
    }

}

class MainViewModelFactory(private val httpKtorService: HttpKtorService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        if (modelClass.equals(MainViewModel::class)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(httpKtorService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}