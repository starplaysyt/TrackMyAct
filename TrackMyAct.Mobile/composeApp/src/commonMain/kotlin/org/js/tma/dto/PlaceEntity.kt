package org.js.tma.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlaceEntity(
    override var id: Int,
    var name: String,
    var description: String,
    var address: String,
    var availableTime: String,

    var events: List<EventEntity> = emptyList()
) : Entity()