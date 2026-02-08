package org.js.tma.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CreateEventRequest(
    var name: String,
    var description: String,
    var eventPlace: PlaceEntity,

    var startDate: LocalDateTime? = null,
    var startTime: LocalDateTime? = null,
    var isAdult: Boolean? = null
)

@Serializable
data class LoginRequest(
    var username: String,
    var password: String
)

@Serializable
data class RegisterOrganizerRequest(
    var login: String,
    var email: String,
    var password: String,
    var name: String,
    var organization: String
)

@Serializable
data class RegisterParticipantRequest(
    var login: String,
    var email: String,
    var password: String,
    var name: String,
    var phone: String,
    var birthDate: LocalDateTime
)