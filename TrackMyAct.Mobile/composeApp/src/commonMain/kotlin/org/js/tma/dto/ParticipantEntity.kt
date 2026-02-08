package org.js.tma.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ParticipantEntity(
    override var id: Int,
    override var username: String,
    override var email: String,
    override var passwordHash: String,
    override var name: String,
    override var roleType: String,

    var birthdayDate: LocalDateTime,
    var phone: String,
    var isVerifed: Boolean,

    var interests: List<InterestEntity> = emptyList(),
    var participations: List<EventEntity> = emptyList(),
    var comments: List<CommentEntity> = emptyList(),
    var archiveEvents: List<EventEntity> = emptyList(),
    var participantRequests: List<EventEntity> = emptyList(),
    var organizerRequests: List<EventEntity> = emptyList(),
    var organizerSubscriptions: List<OrganizerEntity> = emptyList()
) : UserEntity()