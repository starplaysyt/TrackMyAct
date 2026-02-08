package org.js.tma.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrganizerEntity(
    override var id: Int,
    override var username: String,
    override var email: String,
    override var passwordHash: String,
    override var name: String,
    override var roleType: String,

    var organization: String,
    var key: String,
    var organizedEvents: List<EventEntity> = emptyList(),
    var archiveEvents: List<EventEntity> = emptyList(),
    var subscribers: List<ParticipantEntity> = emptyList(),
    var comments: List<CommentEntity> = emptyList()
) : UserEntity()