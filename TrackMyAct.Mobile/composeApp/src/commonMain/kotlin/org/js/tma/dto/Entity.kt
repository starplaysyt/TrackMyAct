package org.js.tma.dto

import kotlinx.serialization.Serializable

@Serializable
abstract class Entity {
    abstract var id: Int
}