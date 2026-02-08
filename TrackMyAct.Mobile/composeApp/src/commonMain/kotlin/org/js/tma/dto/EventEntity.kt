package org.js.tma.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class EventEntity(
    override var id: Int,
    var name: String,
    var description: String,
    var startDateTime: LocalDateTime,
    var needAcception: Boolean,

    var eventPlace: PlaceEntity?,
    var eventPlaceId: Int,

    var organizer: OrganizerEntity?,
    var organizerId: Int,

    var acceptedParticipants: List<ParticipantEntity>? = emptyList(),
    var participantRequests: List<ParticipantEntity>? = emptyList(),
    var organizerRequests: List<ParticipantEntity>? = emptyList()
) : Entity()