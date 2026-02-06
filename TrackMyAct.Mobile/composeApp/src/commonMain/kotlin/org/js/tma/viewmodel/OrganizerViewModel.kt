package org.js.tma.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import org.js.tma.data.ByteArrayWrapper
import org.js.tma.data.stateFlow
import org.js.tma.service.HttpKtorService
import kotlin.reflect.KClass

@Stable
class OrganizerViewModel (private val httpKtorService: HttpKtorService): ViewModel() {

    val organizers by stateFlow(listOf<CategoryData>())
    val loadingState by stateFlow<LoadingState>(LoadingState.NotStarted)

    fun loadOrganizers() {
        viewModelScope.launch {
            loadingState.value = LoadingState.Loading
            try {
                loadingState.value = load()
            } catch (e: Exception) {
                loadingState.value = LoadingState.Failed(error = e.message ?: "Unknown error")
            }
        }
    }

    private suspend fun load() : LoadingState {
        TODO()
    }

}

@Stable
data class OrganizerData(
    val image: ByteArrayWrapper,
    val title: String,
    val description: String,
)

class OrganizerViewModelFactory(private val httpKtorService: HttpKtorService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        if (modelClass.equals(OrganizerViewModel::class)) {
            @Suppress("UNCHECKED_CAST")
            return OrganizerViewModel(httpKtorService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}