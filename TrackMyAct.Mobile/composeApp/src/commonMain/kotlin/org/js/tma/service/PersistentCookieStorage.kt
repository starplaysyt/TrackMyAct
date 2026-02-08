package org.js.tma.service

import androidx.compose.runtime.Stable
import io.ktor.client.plugins.cookies.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import okio.FileSystem
import okio.Path
import org.js.tma.data.SerializableCookie
import org.js.tma.data.toKtorCookie
import org.js.tma.data.toSerializable
import kotlin.time.Clock

@Stable
class PersistentCookieStorage(
    private val filePath: Path,
    private val fileSystem: FileSystem
) : CookiesStorage {
    private val json = Json { ignoreUnknownKeys = true }
    private val cookiesMap = mutableMapOf<String, MutableList<SerializableCookie>>()

    init {
        loadFromDisk()
    }

    override suspend fun get(url: Url): List<Cookie> {
        val now = Clock.System.now().toEpochMilliseconds()
        val hostCookies = cookiesMap[url.host] ?: return emptyList()

        val validCookies = hostCookies.filter { it.expires == null || it.expires > now }

        if (validCookies.size != hostCookies.size) {
            cookiesMap[url.host] = validCookies.toMutableList()
            saveToDisk()
        }

        return validCookies.map { it.toKtorCookie() }
    }

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        val host = requestUrl.host
        val list = cookiesMap.getOrPut(host) { mutableListOf() }
        list.removeAll { it.name == cookie.name }
        list.add(cookie.toSerializable())
        saveToDisk()
    }

    private fun saveToDisk() {
        try {
            val data = json.encodeToString(cookiesMap)
            fileSystem.write(filePath) {
                writeUtf8(data)
            }
        } catch (e: Exception) {
            println("Cookie save error: ${e.message}")
        }
    }

    private fun loadFromDisk() {
        try {
            if (fileSystem.exists(filePath)) {
                val data = fileSystem.read(filePath) { readUtf8() }
                val decoded = json.decodeFromString<Map<String, MutableList<SerializableCookie>>>(data)
                cookiesMap.putAll(decoded)
            }
        } catch (e: Exception) {
            println("Cookie load error: ${e.message}")
        }
    }

    override fun close() {
        try {
            saveToDisk()
            cookiesMap.clear()
        } catch (e: Exception) {}
    }
}