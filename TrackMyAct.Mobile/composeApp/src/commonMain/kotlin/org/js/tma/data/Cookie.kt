package org.js.tma.data

import io.ktor.http.Cookie
import io.ktor.util.date.GMTDate
import kotlinx.serialization.Serializable

@Serializable
data class SerializableCookie(
    val name: String,
    val value: String,
    val domain: String? = null,
    val path: String? = null,
    val expires: Long? = null,
    val secure: Boolean = false,
    val httpOnly: Boolean = false
)

fun Cookie.toSerializable(): SerializableCookie = SerializableCookie(
    name = name,
    value = value,
    domain = domain,
    path = path,
    expires = expires?.timestamp,
    secure = secure,
    httpOnly = httpOnly
)

fun SerializableCookie.toKtorCookie(): Cookie = Cookie(
    name = name,
    value = value,
    domain = domain,
    path = path,
    expires = expires?.let { GMTDate(it) },
    secure = secure,
    httpOnly = httpOnly
)