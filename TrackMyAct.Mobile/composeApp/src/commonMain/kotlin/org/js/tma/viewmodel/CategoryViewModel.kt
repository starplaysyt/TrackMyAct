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
class CategoryViewModel (private val httpKtorService: HttpKtorService): ViewModel() {

    val categories by stateFlow(listOf<CategoryData>())
    val loadingState by stateFlow<LoadingState>(LoadingState.NotStarted)

    fun loadCategories() {
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
data class CategoryData(
    val image: ByteArrayWrapper,
    val title: String,
    val description: String,
)

class CategoryViewModelFactory(private val httpKtorService: HttpKtorService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        if (modelClass.equals(CategoryViewModel::class)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewModel(httpKtorService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}