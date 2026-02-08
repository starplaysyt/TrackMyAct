package org.js.tma.dto

import kotlinx.serialization.Serializable

@Serializable
abstract class UserEntity : Entity() {
    abstract var username: String
    abstract var email: String
    abstract var passwordHash: String
    abstract var name: String
    abstract var roleType: String // Participant/Organizer
}