package org.js.tma.service

import androidx.compose.runtime.Stable
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.js.tma.AppCloser

@Stable
class HttpKtorService {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
                serializersModule = SerializersModule {
                    contextual(LocalDate.serializer())
                }
            })
        }
        install(Logging) {
            level = LogLevel.BODY

            logger = object : Logger {
                override fun log(message: String) {
                    println("HTTP Log: $message")
                }
            }
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 25000
        }
    }

    constructor() {
        AppCloser.setHttpKtorService(this)
    }

    fun getClient(): HttpClient = client

    fun close() { client.close() }

}