package org.js.tma.viewmodel

sealed interface LoadingState {
    object NotStarted : LoadingState
    object Success : LoadingState
    object Loading : LoadingState
    data class Failed(val error: String) : LoadingState
}