package org.js.tma.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterParticipantRequest(
    val login: String,
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val birthday: String,
)
