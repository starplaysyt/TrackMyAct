package org.js.tma.dto

import kotlinx.serialization.Serializable

@Serializable
data class InterestEntity(
    override var id: Int,
    var name: String,
    var description: String,
    var participants: List<ParticipantEntity> = emptyList(),
    var events: List<EventEntity> = emptyList()
) : Entity()