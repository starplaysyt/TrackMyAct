package org.js.tma

import org.js.tma.service.HttpKtorService

object AppCloser {

    private var httpKtorService: HttpKtorService? = null

    fun setHttpKtorService(httpKtorService: HttpKtorService?) {
        this.httpKtorService = httpKtorService
    }

    fun close() {
        if (httpKtorService != null) {
            httpKtorService!!.close()
            httpKtorService = null
        }
    }

}