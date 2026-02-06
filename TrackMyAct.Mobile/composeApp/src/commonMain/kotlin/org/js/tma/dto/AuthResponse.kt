package org.js.tma.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val isSuccess: Boolean,
    val messages: String,
)
