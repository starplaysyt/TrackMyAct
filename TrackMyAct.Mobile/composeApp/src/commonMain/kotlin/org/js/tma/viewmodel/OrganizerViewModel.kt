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
import org.js.tma.service.OrganizerService
import kotlin.reflect.KClass

@Stable
class OrganizerViewModel (private val httpKtorService: HttpKtorService): ViewModel() {

    val organizers by stateFlow(emptyList<OrganizerData>())
    val loadingState by stateFlow<LoadingState>(LoadingState.NotStarted)

    val service = OrganizerService(httpKtorService)

    fun loadOrganizers() {
        viewModelScope.launch {
            loadingState.value = LoadingState.Loading
            try {
                val data = service.getList()
                if (data.first != null) {
                    organizers.value = data.first!!.organizers.map { OrganizerData(
                        image = null,
                        title = it.name,
                        description = it.organization
                    ) }
                }
                loadingState.value = data.second
            } catch (e: Exception) {
                loadingState.value = LoadingState.Failed(error = e.message ?: "Unknown error")
            }
        }
    }

}

@Stable
data class OrganizerData(
    val image: ByteArrayWrapper? = null,
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