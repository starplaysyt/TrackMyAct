package org.js.tma.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    var isSuccess: Boolean,
    var message: String? = null
)

@Serializable
data class GetOrganizersListResponse(
    var organizers: List<OrganizerEntity> = emptyList()
)