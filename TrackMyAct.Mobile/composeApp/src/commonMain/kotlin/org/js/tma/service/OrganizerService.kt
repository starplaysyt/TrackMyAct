package org.js.tma.service

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.js.tma.dto.GetOrganizersListResponse
import org.js.tma.viewmodel.LoadingState
import org.js.tma.viewmodel.OrganizerData

class OrganizerService(private val httpKtorService: HttpKtorService) {

    suspend fun getList() : Pair<GetOrganizersListResponse?, LoadingState> {
        val response = httpKtorService.getClient().get("http://localhost:5241/event/list") {
            contentType(ContentType.Application.Json)
        }

        return if (response.status.value == 200) {
            Pair(
                response.body(),
                LoadingState.Success
            )
        } else Pair(null, LoadingState.Failed(error = "HTTP error: ${response.status.value}"))
    }

}