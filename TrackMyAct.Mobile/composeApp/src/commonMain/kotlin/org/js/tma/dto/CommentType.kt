package org.js.tma.dto

import kotlinx.serialization.Serializable

@Serializable
enum class CommentType {
    FOR_ORGANIZER,
    FOR_EVENT
}