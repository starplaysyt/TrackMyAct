package org.js.tma.viewmodel

sealed interface LoadingState {
    object NotStarted : LoadingState
    object Success : LoadingState
    object Loading : LoadingState
    object Authorized : LoadingState
    object Unauthorized : LoadingState
    data class Failed(val error: String) : LoadingState
}