package org.js.tma.dto

import kotlinx.serialization.Serializable

@Serializable
data class CommentEntity(
    override var id: Int,
    var text: String,
    var type: CommentType,
    var mark: Int,

    var author: ParticipantEntity?,
    var authorId: Int,

    var event: EventEntity?,
    var eventId: Int?,

    var organizer: OrganizerEntity?,
    var organizerId: Int?
) : Entity()