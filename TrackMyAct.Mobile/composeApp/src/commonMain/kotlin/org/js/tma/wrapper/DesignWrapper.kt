package org.js.tma.wrapper

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import org.js.tma.AppTheme
import org.js.tma.getBackground
import org.js.tma.ui.*

enum class AppScreen (val needBottomBar: Boolean = true) {
    Login(false),
    RegStep1(false),
    RegStep2(false),
    RegStep3(false),
    Categories,
    CreateEvent,
    Test
}

@Composable
fun AppPreviewWrapper(
    screenToShow: AppScreen = AppScreen.RegStep3,
    isDarkTheme: Boolean = true
) {
    AppTheme(darkTheme = isDarkTheme) {
        Scaffold (
            bottomBar = {
                if (screenToShow.needBottomBar) {
                    AppBottomBar()
                }
            },
        ) { innerPadding ->
            Box(modifier = getBackground()
                .padding(innerPadding)
                .fillMaxSize()
            ) {
                when (screenToShow) {
                    AppScreen.Login -> LoginScreen(isDarkTheme)
                    AppScreen.RegStep1 -> RegistrationStep1Screen(isDarkTheme)
                    AppScreen.RegStep2 -> RegistrationStep2Screen(isDarkTheme)
                    AppScreen.RegStep3 -> RegistrationStep3Screen(isDarkTheme)
                    AppScreen.Categories -> CategoriesScreen()
                    AppScreen.CreateEvent -> CreateEventScreen()
                    AppScreen.Test -> TestScreen()
                }
            }
        }
    }
}

@Composable
fun AppBottomBar() {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Menu, contentDescription = null) },
            label = { Text("Меню") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.SportsSoccer, contentDescription = null) },
            label = { Text("События") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Главная") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
            label = { Text("Календарь") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Аккаунт") },
            selected = false,
            onClick = {}
        )
    }
}