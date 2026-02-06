package org.js.tma

import org.js.tma.data.stateFlow
import org.js.tma.wrapper.AppBarPage
import org.js.tma.wrapper.AppScreen

object LocalAppData {
    val currentScreen by stateFlow(AppScreen.Test)
    val currentBottomBarSelected by stateFlow(AppBarPage.MAIN)
}